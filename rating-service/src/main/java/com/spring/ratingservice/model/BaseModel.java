package com.spring.ratingservice.model;

import com.spring.ratingservice.util.constraint.Rating;
import com.spring.ratingservice.util.convert.RatingConverter;
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

    @Convert(converter = RatingConverter.class)
    Rating rating;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    boolean deleted;

    @Column(nullable = false, updatable = false)
    Instant createdAt;

    String createdBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }
}
