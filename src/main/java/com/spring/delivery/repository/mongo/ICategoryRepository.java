/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 3:11 PM - 30/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.repository.mongo;

import com.spring.delivery.document.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends MongoRepository<Category, String> {
    boolean existsByName(String name);

    Optional<Category> findByIdAndDeletedIsFalse(String s);

    boolean existsByIdAndDeletedIsFalse(String id);
}