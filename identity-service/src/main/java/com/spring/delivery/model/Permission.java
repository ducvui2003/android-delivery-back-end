package com.spring.delivery.model;

import com.spring.delivery.util.enums.HttpMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Table(name = "permissions")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String path;
    @Enumerated(EnumType.STRING)
    HttpMethod httpMethod;
    String name;
    @ManyToMany(mappedBy = "permissions")
    Set<Role> role;
}
