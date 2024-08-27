package com.spring.productservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "option_items")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class OptionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "option_id")
    Option option;

    boolean deleted = false;

    Instant createdAt;
    Instant updatedAt;
}
