/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:21 PM - 30/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.product;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.request.product.*;
import com.spring.delivery.domain.response.product.CardProductDTO;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.domain.response.product.ProductOptionDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    ApiPaging<CardProductDTO> findAll(int page);

    ApiPaging<CardProductDTO> findAllByCategoryId(String id, int page);

    ProductDTO findById(String id);

    List<ProductDTO> findByIdIn(List<String> ids);

    ProductDTO removeDiscount(String id);

    ProductDTO setDiscount(String id, RequestDiscountCreated request);

    ProductDTO save(RequestProductCreated request);

    ProductDTO updateUrlImage(String id, @Valid RequestUpdateImage request);

    ProductDTO updateProduct(String id, RequestProductUpdated request);

    ProductDTO deleteProduct(String id);

    ProductDTO unDeleteProduct(String id);

    List<ProductDTO> findProductForHomePage();

    ApiPaging<CardProductDTO> searchProduct(RequestSearchProduct request);

    public boolean existsProductById(String id);

    ApiPaging<CardProductDTO> getFavorite(RequestSearchProduct request, Pageable pageable);
}