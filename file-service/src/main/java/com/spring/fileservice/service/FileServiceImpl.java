package com.spring.fileservice.service;

import com.spring.fileservice.model.Resource;
import com.spring.fileservice.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class FileServiceImpl implements FileService {
    ResourceRepository resourceRepository;

    @Override
    public Resource findById(String id) {
        return resourceRepository.findById(id).orElse(null);
    }
}
