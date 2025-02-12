/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 4:59 PM - 04/11/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.service.business.user;

import com.spring.delivery.domain.request.product.RequestProductFavorite;

import java.util.List;

public interface IUserProductFavoriteService {
    List<String> findProductIdByUserId(Long useId, List<String> productIds);

    boolean existsProductFavorite(Long userId, String productId);

    void addProductFavorite(RequestProductFavorite request);

    void removeProductFavorite(RequestProductFavorite request);
}
