/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:00 PM - 04/11/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.product.impl;

import com.spring.delivery.model.UserProductFavorite;
import com.spring.delivery.repository.mysql.IUserProductFavoriteRepository;
import com.spring.delivery.service.product.IUserProductFavoriteService;
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


    @Override
    public List<String> findProductIdByUserId(Long useId, List<String> productIds) {
        return userProductFavoriteRepository.findByUser_IdAndProductIdIsIn(useId, productIds).stream().map(UserProductFavorite::getProductId).toList();
    }

    @Override
    public boolean existsProductFavorite(Long userId, String productId) {
        return userProductFavoriteRepository.existsByUser_IdAndProductId(userId, productId);
    }
}
