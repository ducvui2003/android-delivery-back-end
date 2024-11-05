/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:00 PM - 04/11/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.user.impl;

import com.spring.delivery.domain.request.product.RequestProductFavorite;
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
    public void addProductFavorite(RequestProductFavorite request) {
        var user = productFavoriteHelper(request);
        if (userProductFavoriteRepository.existsByUser_IdAndProductId(user.getId(), request.productId()))
            throw new AppException(AppErrorCode.EXIST);
        userProductFavoriteRepository.save(UserProductFavorite.builder()
                .user(user)
                .productId(request.productId())
                .build());
    }

    @Override
    public void removeProductFavorite(RequestProductFavorite request) {
        if (userProductFavoriteRepository.deleteByProductId(request.productId()) == 0)
            throw new AppException(AppErrorCode.NOT_EXIST);
    }

    private User productFavoriteHelper(RequestProductFavorite request) {
        if (!productRepository.existsById(request.productId()))
            throw new AppException(AppErrorCode.PRODUCT_NOT_FOUND);
        var optionalUserDTO = securityUtil.getCurrentUserDTOFromAccessToken();
        if (optionalUserDTO.isEmpty()) throw new AppException(AppErrorCode.UNAUTHORIZED);
        return User.builder()
                .id(optionalUserDTO.get().id())
                .build();
    }
}
