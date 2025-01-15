/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 3:02 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.product.impl;

import com.spring.delivery.domain.request.product.RequestCategoryCreatedAndUpdated;
import com.spring.delivery.domain.response.product.CategoryDTO;
import com.spring.delivery.mapper.ICategoryMapper;
import com.spring.delivery.repository.mongo.ICategoryRepository;
import com.spring.delivery.service.business.product.ICategoryService;
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
public class CategoryServiceImpl implements ICategoryService {
    ICategoryRepository categoryRepository;
    ICategoryMapper categoryMapper;

    @Override
    public CategoryDTO save(RequestCategoryCreatedAndUpdated category) {
        if (categoryRepository.existsByName(category.name())) throw new AppException(AppErrorCode.EXIST);
        return categoryMapper.toCategoryDTO(categoryRepository.save(categoryMapper.toCategory(category)));
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryDTO).toList();
    }

    @Override
    public CategoryDTO findById(String id) {
        var option = categoryRepository.findByIdAndDeletedIsFalse(id);
        return option.map(categoryMapper::toCategoryDTO).orElseThrow(() -> new AppException(AppErrorCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public boolean existById(String id) {
        return categoryRepository.existsByIdAndDeletedIsFalse(id);
    }

    @Override
    public CategoryDTO update(String id, RequestCategoryCreatedAndUpdated request) {
        var optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) throw new AppException(AppErrorCode.EXIST);
        var category = optionalCategory.get();
        if (request.name() != null) category.setName(request.name());
        if (request.urlImage() != null) category.setUrlImage(request.urlImage());
        return categoryMapper.toCategoryDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO delete(String id) {
        return deleteHelper(id, true);
    }

    @Override
    public CategoryDTO undelete(String id) {
        return deleteHelper(id, false);
    }

    private CategoryDTO deleteHelper(String id, boolean delete) {
        var optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) throw new AppException(AppErrorCode.EXIST);
        var category = optionalCategory.get();
        category.setDeleted(delete);
        return categoryMapper.toCategoryDTO(categoryRepository.save(category));
    }
}
