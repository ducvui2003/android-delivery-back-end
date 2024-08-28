package com.spring.ratingservice.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiPaging<T> {
    private List<T> data;
    private int current;
    private int size;
    private int totalPage;
}
