/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 3:11 PM - 30/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.repository.mongo;

import com.spring.delivery.document.ProductOption;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductOptionRepository extends MongoRepository<ProductOption, String> {
    boolean existsById(@NotNull String id);
}