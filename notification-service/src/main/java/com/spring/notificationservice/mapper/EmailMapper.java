package com.spring.notificationservice.mapper;

import com.spring.event.notification.request.TransactionEmail;
import com.spring.event.notification.request.TransactionEmailTemplate;
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
    @Mapping(target = "textContent", ignore = true)
    public Email toEmail(TransactionEmailTemplate transactionEmailTemplate);

    @Mapping(target = "templateId", ignore = true)
    @Mapping(target = "params", ignore = true)
    public Email toEmail(TransactionEmail transactionEmail);

    @Named("mapTemplateToTemplateId")
    default Integer mapTemplateToTemplateId(String template) {
        return template != null ? TEMPLATE.valueOf(template).getId() : null;
    }
}
