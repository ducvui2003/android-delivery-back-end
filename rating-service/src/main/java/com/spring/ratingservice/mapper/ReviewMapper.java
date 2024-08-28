package com.spring.ratingservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    public static final ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
}
