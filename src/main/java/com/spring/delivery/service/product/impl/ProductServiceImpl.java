/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:19 PM - 30/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.product.impl;

import com.spring.delivery.document.Category;
import com.spring.delivery.document.Product;
import com.spring.delivery.document.ProductOption;
import com.spring.delivery.domain.request.product.RequestDiscountCreated;
import com.spring.delivery.domain.request.product.RequestProductCreated;
import com.spring.delivery.domain.request.product.RequestProductUpdated;
import com.spring.delivery.domain.request.product.RequestUpdateImage;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.mapper.DiscountInfoMapper;
import com.spring.delivery.mapper.ProductMapper;
import com.spring.delivery.repository.mongo.ProductRepository;
import com.spring.delivery.service.product.CategoryService;
import com.spring.delivery.service.product.ProductOptionService;
import com.spring.delivery.service.product.ProductService;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    final ProductRepository productRepository;
    final ProductMapper mapper;
    final DiscountInfoMapper discountInfoMapper;
    final ProductOptionService productOptionService;
    final CategoryService categoryService;
    @Value("${app.paging.size}")
    int pageSize;

    public List<ProductDTO> findAll(int page) {
        return productRepository.findAllByDeletedIsFalse(PageRequest.of(page, pageSize)).stream().map(mapper::toProductDTO).toList();
    }

    @Override
    public List<ProductDTO> findAllByCategoryId(String id, int page) {
        return productRepository.findAllByCategoryIdAndDeletedIsFalse(id, PageRequest.of(page, pageSize)).stream().map(mapper::toProductDTO).toList();
    }

    @Override
    public ProductDTO findById(String id) {
        var optionalProduct = productRepository.findByIdAndDeletedIsFalse(id);
        var product = optionalProduct.stream().findFirst().orElseThrow(() -> new AppException(AppErrorCode.PRODUCT_NOT_FOUND));
        return mapper.toProductDTO(product);
    }

    @Override
    public ProductDTO removeDiscount(String id) {
        var optionalProduct = productRepository.findById(id);
        var product = optionalProduct.stream().findFirst().orElseThrow(() -> new AppException(AppErrorCode.EXIST));
        product.setDiscountInfo(null);
        return mapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO setDiscount(String id, RequestDiscountCreated request) {
        var product = getProductById(id);
        product.setDiscountInfo(discountInfoMapper.toDiscountInfo(request));
        return mapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO save(RequestProductCreated request) {
        var product = mapper.toProduct(request);
        initCategoryAndProductOption(product, request.categoryId(), request.optionIds());
        return mapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateUrlImage(String id, RequestUpdateImage request) {
        var product = getProductById(id);
        product.setImage(request.url());
        return mapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(String id, RequestProductUpdated request) {
        var product = getProductById(id);

        initCategoryAndProductOption(product, request.categoryId(), request.optionIds());
        if (request.name() != null) product.setName(request.name());
        if (request.nutritional() != null) product.setNutritional(mapper.toNutritional(request.nutritional()));
        if (request.quantity() != null) product.setQuantity(request.quantity());
        if (request.description() != null) product.setDescription(request.description());

        return mapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO deleteProduct(String id) {
        var product = getProductById(id);
        product.setDeleted(true);
        return mapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO unDeleteProduct(String id) {
        var product = getProductById(id);
        product.setDeleted(false);
        return mapper.toProductDTO(productRepository.save(product));
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

    private Product getProductById(String id) {
        var optionalProduct = productRepository.findById(id);
        return optionalProduct.stream().findFirst().orElseThrow(() -> new AppException(AppErrorCode.PRODUCT_NOT_FOUND));
    }
}