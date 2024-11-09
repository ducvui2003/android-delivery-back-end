/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 12:44 PM - 05/11/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.util.service;

import com.spring.delivery.domain.request.product.RequestSearchProduct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import static java.lang.Boolean.FALSE;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductHelper {
    @Value("${app.paging.size}")
    int pageSize;

    public Query mapperToQuerySearch(RequestSearchProduct request) {
        var query = mapperToQueryCount(request);
        var page = request.page() == null ? 0 : request.page();
        query.skip((long) pageSize * (page - 1));
        query.limit(pageSize * page);
        return query;
    }

    public Query mapperToQueryCount(RequestSearchProduct request) {
        var query = new Query();
        if (request.name() != null && !request.name().isBlank())
            query.addCriteria(Criteria.where("name").regex(request.name()));
        if (request.category() != null && !request.category().isBlank())
            query.addCriteria(Criteria.where("category").regex(request.category()));
        if (request.isNew() != null) {
            if (request.isNew()) query.with(Sort.by(Sort.Direction.ASC, "createdAt"));
            else query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        }


        if (request.isBestSeller() != null && request.isBestSeller()) {
            // Đợi Bills
        }
        return query;
    }
}
