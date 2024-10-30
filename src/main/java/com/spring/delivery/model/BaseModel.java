package com.spring.delivery.model;

import com.spring.delivery.util.enums.Rating;
import com.spring.delivery.util.enums.converter.RatingConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    boolean deleted = Boolean.FALSE;

    @Column(nullable = false, updatable = false)
    Instant createdAt;

    String createdBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }
}
