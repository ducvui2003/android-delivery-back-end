/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:00 PM - 04/11/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.user.impl;

import com.spring.delivery.domain.ApiPaging;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.model.User;
import com.spring.delivery.model.UserProductFavorite;
import com.spring.delivery.repository.mongo.IProductRepository;
import com.spring.delivery.repository.mysql.IUserProductFavoriteRepository;
import com.spring.delivery.service.business.user.IUserProductFavoriteService;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserProductFavoriteServiceImpl implements IUserProductFavoriteService {
    IUserProductFavoriteRepository userProductFavoriteRepository;
    IProductRepository productRepository;
    SecurityUtil securityUtil;


    @Override
    public List<String> findProductIdByUserId(Long useId, List<String> productIds) {
        return userProductFavoriteRepository.findByUser_IdAndProductIdIsIn(useId, productIds).stream().map(UserProductFavorite::getProductId).toList();
    }

    @Override
    public boolean existsProductFavorite(Long userId, String productId) {
        return userProductFavoriteRepository.existsByUser_IdAndProductId(userId, productId);
    }

    @Override
    public void addProductFavorite(String id) {
        var user = productFavoriteHelper(id);
        if (userProductFavoriteRepository.existsByUser_IdAndProductId(user.getId(), id))
            throw new AppException(AppErrorCode.EXIST);
        userProductFavoriteRepository.save(UserProductFavorite.builder()
                .user(user)
                .productId(id)
                .build());
    }

    @Override
    public void removeProductFavorite(String id) {
        if (userProductFavoriteRepository.deleteByProductId(id) == 0)
            throw new AppException(AppErrorCode.NOT_EXIST);
    }

    @Override
    public List<String> findProductIdByUserId(Long useId, Pageable pageable) {
        return userProductFavoriteRepository.findByUser_Id(useId, pageable).getContent().stream().map(UserProductFavorite::getProductId).toList();
    }
    @Override
    public List<String> findProductIdByUserId(Long useId) {
        return userProductFavoriteRepository.findByUser_Id(useId).stream().map(UserProductFavorite::getProductId).toList();
    }

    private User productFavoriteHelper(String id) {
        var product = productRepository.findByIdAndDeletedIsFalse(id);
        if (product.isEmpty() || product.get().isDeleted())
            throw new AppException(AppErrorCode.PRODUCT_NOT_FOUND);
        var optionalUserDTO = securityUtil.getCurrentUserDTOFromAccessToken();
        if (optionalUserDTO.isEmpty()) throw new AppException(AppErrorCode.UNAUTHORIZED);
        User user = new User();
        user.setId(optionalUserDTO.get().id());
        return user;
    }
}
