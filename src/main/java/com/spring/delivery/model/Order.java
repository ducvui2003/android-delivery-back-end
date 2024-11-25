package com.spring.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.util.enums.PaymentMethod;
import com.spring.delivery.util.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;

import java.util.List;
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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double price;

    Integer starReview;

    @Enumerated(EnumType.STRING)
    StatusOrder status;

    @OneToOne(mappedBy = "order")
    OrderDetail orderDetail;
}
