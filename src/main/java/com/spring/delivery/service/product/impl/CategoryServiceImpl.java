/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 3:02 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.product.impl;

import com.spring.delivery.document.Category;
import com.spring.delivery.domain.request.product.RequestCategoryCreated;
import com.spring.delivery.domain.response.product.CategoryDTO;
import com.spring.delivery.mapper.CategoryMapper;
import com.spring.delivery.repository.mongo.CategoryRepository;
import com.spring.delivery.service.product.CategoryService;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public CategoryDTO save(RequestCategoryCreated category) {
        return categoryMapper.toCategoryDTO(categoryRepository.save(categoryMapper.toCategory(category)));
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryDTO).toList();
    }

    @Override
    public CategoryDTO findById(String id) {
        var option = categoryRepository.findById(id);
        return option.map(categoryMapper::toCategoryDTO).orElseThrow(() -> new AppException(AppErrorCode.CATEGORY_NOT_FOUND));
    }
}
