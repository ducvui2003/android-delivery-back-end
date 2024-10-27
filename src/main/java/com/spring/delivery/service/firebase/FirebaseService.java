package com.spring.delivery.service.firebase;

import com.spring.delivery.document.Resource;
import com.spring.delivery.util.enums.Folder;

import java.io.InputStream;

public interface FirebaseService {
    Resource save(InputStream inputStream, String contentType) throws Exception;

    Resource save(Folder folder, InputStream inputStream, String contentType) throws Exception;

    boolean delete(String fileUrl);
}
