package com.spring.delivery.domain.response.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.delivery.domain.response.promotion.PromotionOrderResponse;
import com.spring.delivery.model.OrderItemOption;
import com.spring.delivery.util.enums.PaymentMethod;
import com.spring.delivery.util.enums.StatusOrder;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrderDetail {
    Long id;
    String fullName;
    String phone;
    String email;
    String address;
    PaymentMethod paymentMethod;
    Set<PromotionOrderResponse> promotions;
    Double subTotal;
    Double deliveryFee;
    Double discount;
    String description;
    String reasonForCancellation;
    int starReview;
    StatusOrder status;

    List<ResponseOrderItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseOrderItem {
        String productId;
        String name;
        String category;
        double price;
        String quantity;
        String image;
        Integer starReview;

        List<OrderItemOption> options;
    }
}
