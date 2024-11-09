/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 3:01 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.product;

import com.spring.delivery.domain.request.product.RequestCategoryCreatedAndUpdated;
import com.spring.delivery.domain.response.product.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO save(RequestCategoryCreatedAndUpdated category);

    List<CategoryDTO> findAll();

    CategoryDTO findById(String id);

    boolean existById(String id);

    CategoryDTO update(String id, RequestCategoryCreatedAndUpdated category);

    CategoryDTO delete(String id);

    CategoryDTO undelete(String id);
}
