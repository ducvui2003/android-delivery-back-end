package com.spring.delivery.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "products")
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@TypeAlias("Product")
public class Product {
    @MongoId
    @Field(targetType = FieldType.STRING)
    String id;

    String name;

    String image;

    String description;

    Integer quantity;

    Double price;

    @DocumentReference(lazy = true)
    Category category;

    @DocumentReference(lazy = true)
    List<ProductOption> options;

    List<Nutritional> nutritional;

    DiscountInfo discountInfo;

    Boolean deleted = false;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]", timezone = "Asia/Ho_Chi_Minh")
    LocalDateTime createdAt;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]", timezone = "Asia/Ho_Chi_Minh")
    LocalDateTime updateAt;

    @CreatedBy
    String createdBy;
}
