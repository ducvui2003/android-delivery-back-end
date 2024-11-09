package com.spring.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.util.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "order_details")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ElementCollection
    List<String> productIds;

    String address;

    @Enumerated(EnumType.STRING)
    PaymentMethod parameterModel;

    @ElementCollection
    Set<String> promotionIds;

    Double subTotal;

    Double deliveryFee;

    Double discount;

    String description;

    String reasonForCancellation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;
}
