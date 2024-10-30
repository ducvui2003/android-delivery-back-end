package com.spring.delivery.repository.mongo;

import com.spring.delivery.document.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceRepository extends MongoRepository<Resource, String> {
    Optional<Resource> findById(String id);
}
