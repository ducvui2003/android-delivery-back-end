package com.spring.delivery.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.internal.build.AllowPrintStacktrace;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "carts")
@Table
@Data
@AllowPrintStacktrace
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart extends BaseModel {
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItems;
}
