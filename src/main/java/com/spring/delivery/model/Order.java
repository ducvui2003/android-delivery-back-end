package com.spring.delivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.util.enums.PaymentMethod;
import com.spring.delivery.util.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;
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
public class Order extends DeletedModel {
    @JoinColumn(name = "user_id")
    Long userId;

    Double price;

    @ElementCollection
    List<String> images;

    Integer starReview;

    @Enumerated(EnumType.STRING)
    StatusOrder status;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    OrderDetail orderDetail;

}
