package com.spring.delivery.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.spring.delivery.util.enums.converter.AuthTypeConverter;
import com.spring.delivery.util.enums.AuthType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String phoneNumber;

    String email;

    @JsonIgnore
    String password;

    String fullName;

    boolean verified;

    @Column(nullable = false)
    @Convert(converter = AuthTypeConverter.class)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    AuthType authType = AuthType.USERNAME_PASSWORD;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @OneToMany(mappedBy = "user")
    List<UserProductFavorite> productFavorites;
}
