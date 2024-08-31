package com.spring.ratingservice.util.convert;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


public class SortConverter {
    public static Pageable convert(String sortField, String sortDirection, int page, int size) {
        Sort sort = detachSort(sortField, sortDirection);
        Pageable pageable = PageRequest.of(page, size, sort);
        return null;
    }

    private static Sort detachSort(String sortField, String sortDirection) {
//        List<Sort.Order> orders = sosos
        return null;
    }
}
