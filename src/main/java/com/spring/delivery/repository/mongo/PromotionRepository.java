package com.spring.delivery.repository.mongo;

import com.spring.delivery.document.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {
    @Query("{'_id': ObjectId('?0')}")
    Promotion findPromotionById(String id);

}
