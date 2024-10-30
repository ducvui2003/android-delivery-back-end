package com.spring.delivery.mapper;

import com.spring.delivery.domain.response.file.ResponseFileUpload;
import com.spring.delivery.document.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "fileType", source = "metadata.contentType")
    @Mapping(target = "size", source = "metadata.size")
    @Mapping(target = "createdAt", source = "createdAt")
    ResponseFileUpload toResponseFileUpload(Resource resource);
}
