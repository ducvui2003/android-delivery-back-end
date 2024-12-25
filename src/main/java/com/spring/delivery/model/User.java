package com.spring.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.util.enums.AuthType;
import com.spring.delivery.util.enums.converter.AuthTypeConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseModel {


    @Column(name = "phone_number")
    String phoneNumber;

    String email;

    @JsonIgnore
    String password;

    @Column(name = "full_name")
    String fullName;

    boolean verified;

    String sex;

    String avatar;

    String birthday;

    @Column(nullable = false)
    @Convert(converter = AuthTypeConverter.class)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    AuthType authType = AuthType.USERNAME_PASSWORD;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    Set<Permission> permissions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Address> address;

    @OneToMany(mappedBy = "user")
    List<UserProductFavorite> productFavorites;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Cart cart;

}
