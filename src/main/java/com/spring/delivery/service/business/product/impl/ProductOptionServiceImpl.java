/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:32 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.product.impl;

import com.spring.delivery.domain.request.product.RequestOptionCreated;
import com.spring.delivery.domain.response.product.ProductOptionDTO;
import com.spring.delivery.mapper.ProductOptionMapper;
import com.spring.delivery.repository.mongo.IProductOptionRepository;
import com.spring.delivery.service.business.product.IProductOptionService;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements IProductOptionService {
    IProductOptionRepository productOptionRepository;
    ProductOptionMapper mapper;

    @Override
    public ProductOptionDTO save(RequestOptionCreated productOption) {
        return mapper.toProductOptionDTO(productOptionRepository.save(mapper.toProductOption(productOption)));
    }

    @Override
    public List<ProductOptionDTO> findAll() {
        return productOptionRepository.findAll().stream().map(mapper::toProductOptionDTO).toList();
    }

    @Override
    public ProductOptionDTO findById(String id) {
        return productOptionRepository.findById(id).map(mapper::toProductOptionDTO).orElseThrow(() -> new AppException(AppErrorCode.PRODUCT_OPTION_NOT_FOUND));
    }

    @Override
    public boolean existById(String id) {
        return productOptionRepository.existsById(id);
    }
}
