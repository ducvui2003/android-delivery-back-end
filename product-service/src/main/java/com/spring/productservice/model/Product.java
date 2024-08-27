package com.spring.productservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @ManyToOne
    Category category;
    BigDecimal basePrice;
    BigDecimal salePrice;
    String shortDescription;
    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    boolean deleted = false;

    Instant createdAt;
    Instant updatedAt;
}
