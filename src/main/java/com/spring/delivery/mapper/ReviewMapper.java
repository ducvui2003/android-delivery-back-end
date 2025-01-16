package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.review.ProductReviewDetailResponse;
import com.spring.delivery.model.ReviewProduct;
import com.spring.delivery.util.enums.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    @Mapping(target = "rating", source = "rating", qualifiedByName = "mapRatingToRatingValue")
    public ProductReviewDetailResponse toProductRatingDetailDTO(ReviewProduct reviewProduct);

    @Named("mapRatingToRatingValue")
    default Integer mapRatingToRatingValue(Rating rating) {
        return rating != null ? rating.getValue() : null;
    }
}
