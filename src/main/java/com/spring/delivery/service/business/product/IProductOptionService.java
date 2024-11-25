/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:29 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.product;

import com.spring.delivery.domain.request.product.RequestOptionCreated;
import com.spring.delivery.domain.response.product.ProductOptionDTO;

import java.util.List;

public interface IProductOptionService {
    ProductOptionDTO save(RequestOptionCreated category);

    List<ProductOptionDTO> findAll();

    ProductOptionDTO findById(String id);

    boolean existById(String id);
}
