package com.spring.delivery.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.util.enums.converter.PromotionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "promotions")
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@TypeAlias("Promotion")
public class Promotion {
    @MongoId
    @Field(targetType = FieldType.STRING)
    String id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    String name;

    String description;

    String promotionCode;

    String applicableScope;

    String termsAndConditions;

    DiscountPromotionInfo discountPromotionInfo;

    @Enumerated(EnumType.STRING)
    PromotionType type;

    @Builder.Default
    Boolean deleted = false;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updateAt;

    @CreatedBy
    String createdBy;

    @LastModifiedBy
    String updatedBy;
}
