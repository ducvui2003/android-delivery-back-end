/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 2:27 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.mapper;

import com.spring.delivery.document.Nutritional;
import com.spring.delivery.document.Product;
import com.spring.delivery.domain.request.product.RequestNutritionalCreated;
import com.spring.delivery.domain.request.product.RequestProductCreated;
import com.spring.delivery.domain.response.product.CardProductDTO;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.util.FirebaseUrlUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {
    @Mapping(target = "image", source = "image", qualifiedByName = "mapImageUrl")
    ProductDTO toProductDTO(Product product);

    @Mapping(target = "image", source = "image", qualifiedByName = "mapImageUrl")
    CardProductDTO toCardProductDTO(Product product);

    Product toProduct(RequestProductCreated request);

    List<Nutritional> toNutritional(List<RequestNutritionalCreated> requests);

    @Named("mapImageUrl")
    default String mapImageUrl(String firebaseUrl) {
        return FirebaseUrlUtil.getPublicUrl(firebaseUrl);
    }
}