package com.spring.delivery.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Product {
    @Id
    ObjectId id;
    String name;
    String image;
    String description;
    Integer quantity;
    Double price;
    @DBRef
    Category category;
    @DBRef
    List<Option> options;
    @DBRef
    Nutritional nutritional;
}
