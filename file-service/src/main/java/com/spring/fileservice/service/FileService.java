package com.spring.fileservice.service;

import com.spring.fileservice.model.Resource;

public interface FileService {
    Resource findById(String id);
}
