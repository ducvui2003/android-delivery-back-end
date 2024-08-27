package com.spring.productservice.mapper;

import com.spring.productservice.domain.response.ResponseProduct;
import com.spring.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    public static ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "description", source = "shortDescription")
    ResponseProduct toResponseProduct(Product product);
}
