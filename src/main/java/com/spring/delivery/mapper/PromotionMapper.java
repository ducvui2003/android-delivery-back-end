package com.spring.delivery.mapper;

import com.spring.delivery.document.Promotion;
import com.spring.delivery.domain.request.promotion.RequestPromotionCreated;
import com.spring.delivery.domain.response.promotion.PromotionBaseDTO;
import com.spring.delivery.domain.response.promotion.PromotionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    PromotionDTO toPromotionDTO(Promotion promotion);

    PromotionBaseDTO toPromotionBaseDTO(Promotion promotion);

    Promotion toPromotion(RequestPromotionCreated req);
}
