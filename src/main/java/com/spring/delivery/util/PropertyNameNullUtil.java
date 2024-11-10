package com.spring.delivery.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class PropertyNameNullUtil {
    private PropertyNameNullUtil() {
        throw new AssertionError();
    }

    public static String[] getNullPropertyNames(Object source) {
        Set<String> emptyNames = findNullProperties(source);
        return emptyNames.toArray(new String[0]);
    }

    private static Set<String> findNullProperties(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        Set<String> emptyNames = new HashSet<>();

        for (var propertyDescriptor : wrappedSource.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            if (wrappedSource.getPropertyValue(propertyName) == null) {
                emptyNames.add(propertyName);
            }
        }
        return emptyNames;
    }
}
