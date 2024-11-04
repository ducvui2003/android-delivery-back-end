/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 3:11 PM - 30/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.repository.mongo;

import com.spring.delivery.document.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByCategoryIdAndDeletedIsFalse(String id, PageRequest pageRequest);

    List<Product> findAllByDeletedIsFalse(PageRequest pageRequest);

    Optional<Product> findByIdAndDeletedIsFalse(String id);

    boolean existsById(@NotNull String id);

    List<Product> findByIdIsIn(List<String> ids);
}