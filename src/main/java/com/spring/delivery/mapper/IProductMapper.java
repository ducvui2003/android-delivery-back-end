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
import com.spring.delivery.domain.request.product.RequestProductUpdated;
import com.spring.delivery.domain.response.product.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductDTO toProductDTO(Product product);

    Product toProduct(RequestProductCreated request);

    Product toProduct(RequestProductUpdated request);

    List<Nutritional> toNutritional(List<RequestNutritionalCreated> requests);
}