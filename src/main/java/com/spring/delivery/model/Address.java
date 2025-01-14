package com.spring.delivery.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NegativeOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "address")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address extends BaseModel {
    String name;
    String address;

    @Column(name = "is_default", columnDefinition = "boolean default false")
    boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
