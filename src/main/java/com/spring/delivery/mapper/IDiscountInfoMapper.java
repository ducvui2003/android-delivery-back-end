/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 6:14 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.mapper;

import com.spring.delivery.document.DiscountInfo;
import com.spring.delivery.domain.request.product.RequestDiscountCreated;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDiscountInfoMapper {
    DiscountInfo toDiscountInfo(RequestDiscountCreated request);
}