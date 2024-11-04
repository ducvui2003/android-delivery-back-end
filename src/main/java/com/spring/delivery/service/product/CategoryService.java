/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 3:01 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.product;

import com.spring.delivery.domain.request.product.RequestCategoryCreated;
import com.spring.delivery.domain.response.product.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO save(RequestCategoryCreated category);
    List<CategoryDTO> findAll();
    CategoryDTO findById(String id);
}
