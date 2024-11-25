package com.spring.delivery.repository.mongo;

import com.spring.delivery.document.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {
    @Query("{'userIds': {'$in':  [null, ?0]}}")
    List<Promotion> findPromotionsByUserId(Long id);

}
