/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:19 PM - 30/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.product.impl;

import com.spring.delivery.document.Category;
import com.spring.delivery.document.Product;
import com.spring.delivery.document.ProductOption;
import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.product.*;
import com.spring.delivery.domain.response.product.CardProductDTO;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.domain.response.review.AverageRatingProduct;
import com.spring.delivery.mapper.IDiscountInfoMapper;
import com.spring.delivery.mapper.IProductMapper;
import com.spring.delivery.repository.mongo.IProductRepository;
import com.spring.delivery.service.business.product.ICategoryService;
import com.spring.delivery.service.business.product.IProductOptionService;
import com.spring.delivery.service.business.product.IProductService;
import com.spring.delivery.service.business.review.IReviewProductService;
import com.spring.delivery.service.business.user.IUserProductFavoriteService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import com.spring.delivery.util.service.ProductHelper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements IProductService {
    IProductRepository productRepository;
    IProductMapper productMapper;
    IDiscountInfoMapper discountInfoMapper;
    IProductOptionService productOptionService;
    ICategoryService categoryService;
    IReviewProductService reviewProductService;
    SecurityUtil securityUtil;
    IUserProductFavoriteService userProductFavoriteService;
    MongoTemplate mongoTemplate;
    ProductHelper productHelper;

    @Value("${app.paging.size}")
    @NonFinal
    int pageSize;

    public ApiPaging<CardProductDTO> findAll(int page) {
        var pageProducts = productRepository.findAllByDeletedIsFalse(PageRequest.of(Math.max(page, 1) - 1, pageSize, Sort.by("id").descending()));
        return findCardProductHelper(pageProducts, Math.max(page, 1));
    }

    @Override
    public ApiPaging<CardProductDTO> findAllByCategoryId(String id, int page) {
        var pageProducts = productRepository.findAllByCategoryIdAndDeletedIsFalse(id, PageRequest.of(Math.max(page, 1) - 1, pageSize, Sort.by("id").descending()));
        return findCardProductHelper(pageProducts, Math.max(page, 1));
    }

    @Override
    public ProductDTO findById(String id) {
        var optionalProduct = productRepository.findByIdAndDeletedIsFalse(id);
        var product = optionalProduct.stream().findFirst().orElseThrow(() -> new AppException(AppErrorCode.PRODUCT_NOT_FOUND));
        var favorite = securityUtil.getCurrentUserDTOFromAccessToken().map(userDTO -> userProductFavoriteService.existsProductFavorite(userDTO.id(), product.getId())).orElse(false);
        var productDto = productMapper.toProductDTO(product);
        productDto.setRating(reviewProductService.getRatingOverall(id));
        productDto.setFavorite(favorite);
        return productDto;
    }

    @Override
    public ProductDTO removeDiscount(String id) {
        var optionalProduct = productRepository.findById(id);
        var product = optionalProduct.stream().findFirst().orElseThrow(() -> new AppException(AppErrorCode.EXIST));
        product.setDiscountInfo(null);
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO setDiscount(String id, RequestDiscountCreated request) {
        var product = getProductById(id);
        product.setDiscountInfo(discountInfoMapper.toDiscountInfo(request));
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO save(RequestProductCreated request) {
        var product = productMapper.toProduct(request);
        initCategoryAndProductOption(product, request.categoryId(), request.optionIds());
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateUrlImage(String id, RequestUpdateImage request) {
        var product = getProductById(id);
        product.setImage(request.url());
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(String id, RequestProductUpdated request) {
        var product = getProductById(id);

        initCategoryAndProductOption(product, request.categoryId(), request.optionIds());
        if (request.name() != null) product.setName(request.name());
        if (request.nutritional() != null) product.setNutritional(productMapper.toNutritional(request.nutritional()));
        if (request.quantity() != null) product.setQuantity(request.quantity());
        if (request.description() != null) product.setDescription(request.description());

        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO deleteProduct(String id) {
        var product = getProductById(id);
        product.setDeleted(true);
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO unDeleteProduct(String id) {
        var product = getProductById(id);
        product.setDeleted(false);
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public List<ProductDTO> findProductForHomePage() {
        var ids = reviewProductService.findTopAverageRatingProduct().stream().map(AverageRatingProduct::productId).toList();
        return productRepository.findByIdIsInAndDeletedIsFalse(ids).stream().map(productMapper::toProductDTO).sorted(Comparator.comparing(ProductDTO::getName)).limit(pageSize).toList();
    }

    @Override
    public ApiPaging<CardProductDTO> searchProduct(RequestSearchProduct request) {
        var querySearch = productHelper.mapperToQuerySearch(request);
        var queryCount = productHelper.mapperToQueryCount(request);
        var products = mongoTemplate.find(querySearch, Product.class);
        var total = mongoTemplate.count(queryCount, Product.class);
        var paging = searchCardProductHelper(products, request.page() == null ? 1 : Math.max(request.page(), 1));
        paging.setSize(products.size());
        paging.setTotalPage((int) (total % pageSize != 0 ? (total / pageSize) + 1 : total / pageSize));
        return paging;
    }

    @Override
    public boolean existsProductById(String id) {
        return getProductByIdAndCategoryHasDeletedIsFalse(id) != null;
    }

    private void initCategoryAndProductOption(Product product, String categoryId, List<String> productOptions) {
        if (categoryId != null && !categoryService.existById(categoryId))
            throw new AppException(AppErrorCode.CATEGORY_NOT_FOUND);
        if (productOptions != null && productOptions.stream().noneMatch(productOptionService::existById))
            throw new AppException(AppErrorCode.PRODUCT_OPTION_NOT_FOUND);


        if (categoryId != null) product.setCategory(Category.builder().id(categoryId).build());
        if (productOptions != null && !productOptions.isEmpty())
            product.setOptions(productOptions.stream().map(optionId -> ProductOption.builder().id(optionId).build()).toList());
    }

    private Product getProductByIdAndCategoryHasDeletedIsFalse(String id) {
        var product = getProductById(id);
        return product != null && !product.getCategory().isDeleted() ? product : null;
    }

    private Product getProductById(String id) {
        var optionalProduct = productRepository.findById(id);
        return optionalProduct.stream().findFirst().orElseThrow(() -> new AppException(AppErrorCode.PRODUCT_NOT_FOUND));
    }

    private List<String> getProductFavorite(List<Product> productDTOs) {
        var optionalUser = securityUtil.getCurrentUserDTOFromAccessToken();
        return optionalUser.map(userDTO -> userProductFavoriteService.findProductIdByUserId(userDTO.id(), productDTOs.stream().map(Product::getId).toList())).orElse(new ArrayList<>());
    }

    private ApiPaging<CardProductDTO> findCardProductHelper(Page<Product> pageProducts, int page) {
        var products = pageProducts.getContent().stream().filter(pro -> !pro.getCategory().isDeleted()).toList();

        var listProductFavorite = getProductFavorite(products);
        var listAvgRating = reviewProductService.findAverageRatingProduct(products.stream().map(Product::getId).toList());
        var productDTOs = products.stream().map(it -> {
            var productDTO = productMapper.toCardProductDTO(it);
            productDTO.setFavorite(listProductFavorite.contains(it.getId()));
            productDTO.setAvgRating(listAvgRating.stream().filter(avgRating -> avgRating.productId().equals(it.getId())).findFirst().orElse(new AverageRatingProduct(it.getId(), 0.0)).averageRating());
            return productDTO;
        }).toList();

        return ApiPaging.<CardProductDTO>builder().content(productDTOs).totalPage(pageProducts.getTotalPages()).current(page).size(products.size()).build();
    }

    private ApiPaging<CardProductDTO> searchCardProductHelper(List<Product> products, int page) {
        var listProductFavorite = getProductFavorite(products);
        products = products.stream().filter(pro -> !pro.getCategory().isDeleted()).toList();
        var productDTOs = products.stream().map(it -> {
            var productDTO = productMapper.toCardProductDTO(it);
            productDTO.setFavorite(listProductFavorite.contains(it.getId()));
            return productDTO;
        }).toList();

        return ApiPaging.<CardProductDTO>builder().content(productDTOs).current(page).build();
    }
}