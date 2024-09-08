package com.spring.fileservice.service;

import com.spring.fileservice.domain.response.ResponseFileUpload;
import com.spring.fileservice.model.Resource;
import com.spring.fileservice.util.constraint.FOLDER;

import java.io.InputStream;

public interface FirebaseService {
    Resource save(InputStream inputStream, String contentType) throws Exception;

    Resource save(FOLDER folder, InputStream inputStream, String contentType) throws Exception;

    boolean delete(String fileUrl);

}
