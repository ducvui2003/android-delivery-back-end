package com.spring.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.util.convert.JsonOrderPromotionConverter;
import com.spring.delivery.util.enums.PaymentMethod;
import com.spring.delivery.util.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Table(name = "orders")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order extends DeletedModel {
    @JoinColumn(name = "user_id")
    Long userId;

    String fullName;
    String phone;
    String email;
    String address;
    Integer starReview;

    @Column(nullable = false, columnDefinition = "double default 0.0")
    double shippingFee;

    @Column(columnDefinition = "JSON")
    @Convert(converter = JsonOrderPromotionConverter.class)
    OrderPromotion promotionShip;

    @Column(columnDefinition = "JSON")
    @Convert(converter = JsonOrderPromotionConverter.class)
    OrderPromotion promotionProduct;


    @Enumerated(EnumType.STRING)
    StatusOrder status;

    Double subTotal;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    String reasonForCancellation;
}
