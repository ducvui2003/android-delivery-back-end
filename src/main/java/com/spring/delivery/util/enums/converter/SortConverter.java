package com.spring.delivery.util.enums.converter;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SortConverter {
    private SortConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Pageable convert(String sortField, String sortDirection, int page, int size) {
        Sort sort = detachSort(sortField, sortDirection);
        return PageRequest.of(page - 1, size, sort);
    }

    private static Sort detachSort(String sortField, String sortDirection) {
        return Sort.by(Sort.Direction.fromString(sortDirection), sortField);
    }
}
