package com.spring.ratingservice.mapper;

import com.spring.ratingservice.domain.ProductReviewDetailDTO;
import com.spring.ratingservice.model.ReviewProduct;
import com.spring.ratingservice.util.constraint.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    public static final ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "rating", source = "rating", qualifiedByName = "mapRatingToRatingValue")
    public ProductReviewDetailDTO toProductRatingDetailDTO(ReviewProduct reviewProduct);

    @Named("mapRatingToRatingValue")
    default Integer mapRatingToRatingValue(Rating rating) {
        return rating != null ? rating.getValue() : null;
    }
}
