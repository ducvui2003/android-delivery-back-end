package com.spring.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.util.convert.JsonOrderItemOptionConverter;
import com.spring.delivery.util.convert.JsonOrderPromotionConverter;
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
@Table(name = "order_items")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItem extends BaseModel {
    String productId;
    String name;
    String category;
    double price;
    String quantity;
    String thumbnail;
    Integer starReview;

    @Column(columnDefinition = "JSON")
    @Convert(converter = JsonOrderItemOptionConverter.class)
    List<OrderItemOption> options;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;
}
