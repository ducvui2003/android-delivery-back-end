package com.spring.delivery.service.business.file;

import com.spring.delivery.document.Resource;
import com.spring.delivery.repository.mongo.ResourceRepository;
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
