package com.spring.delivery.model;

import com.spring.delivery.util.convert.StringListToJsonConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.internal.build.AllowPrintStacktrace;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "cart_items")
@Table
@Data
@AllowPrintStacktrace
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;
    String productId;
    @Convert(converter = StringListToJsonConverter.class)
    @Column(columnDefinition = "json")
    List<String> optionIds;
    Integer quantity;
}
