/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:21 PM - 30/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.product;

import com.spring.delivery.domain.request.product.RequestDiscountCreated;
import com.spring.delivery.domain.request.product.RequestProductCreated;
import com.spring.delivery.domain.request.product.RequestProductUpdated;
import com.spring.delivery.domain.request.product.RequestUpdateImage;
import com.spring.delivery.domain.response.product.ProductDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll(int page);

    List<ProductDTO> findAllByCategoryId(String id, int page);

    ProductDTO findById(String id);

    ProductDTO removeDiscount(String id);

    ProductDTO setDiscount(String id, RequestDiscountCreated request);

    ProductDTO save(RequestProductCreated request);

    ProductDTO updateUrlImage(String id, @Valid RequestUpdateImage request);

    ProductDTO updateProduct(String id, RequestProductUpdated request);

    ProductDTO deleteProduct(String id);

    ProductDTO unDeleteProduct(String id);
}