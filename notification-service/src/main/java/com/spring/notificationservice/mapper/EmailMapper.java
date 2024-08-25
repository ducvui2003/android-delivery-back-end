package com.spring.notificationservice.mapper;

import com.spring.notificationservice.domain.request.RequestTransactionEmail;
import com.spring.notificationservice.domain.request.RequestTransactionEmailTemplate;
import com.spring.notificationservice.model.Email;
import com.spring.notificationservice.util.constrant.TEMPLATE;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmailMapper {
    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);

    @Mapping(target = "templateId", source = "template", qualifiedByName = "mapTemplateToTemplateId")
    public Email toEmail(RequestTransactionEmailTemplate transactionEmailTemplate);

    public Email toEmail(RequestTransactionEmail transactionEmail);

    @Named("mapTemplateToTemplateId")
    default Integer mapTemplateToTemplateId(TEMPLATE template) {
        return template != null ? template.getId() : null;
    }
}
