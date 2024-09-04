package com.spring.ratingservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiPaging<T> {
    List<T> content;
    int current;
    int size;
    int totalPage;
}
