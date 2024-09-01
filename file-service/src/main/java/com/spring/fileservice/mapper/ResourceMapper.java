package com.spring.fileservice.mapper;

import com.spring.fileservice.domain.response.ResponseFileUpload;
import com.spring.fileservice.model.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResourceMapper {
    public static final ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "fileType", source = "metadata.contentType")
    @Mapping(target = "size", source = "metadata.size")
    @Mapping(target = "createdAt", source = "createdAt")
    ResponseFileUpload toResponseFileUpload(Resource resource);
}
