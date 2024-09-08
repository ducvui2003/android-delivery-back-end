package com.spring.fileservice.service;

import com.google.cloud.storage.*;
import com.spring.fileservice.model.Metadata;
import com.spring.fileservice.model.Resource;
import com.spring.fileservice.repository.ResourceRepository;
import com.spring.fileservice.util.constraint.FOLDER;
import com.spring.fileservice.util.naming.NamingStrategy;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class FirebaseServiceImpl implements FirebaseService {
    Storage storage = StorageOptions.getDefaultInstance().getService();
    NamingStrategy namingStrategy;
    Bucket bucket;
    @NonFinal
    @Value("${app.service.firebase.bucket}")
    String storageBucket;
    ResourceRepository resourceRepository;


    @Override
    public Resource save(InputStream inputStream, String contentType) throws Exception {
        return save(null, inputStream, contentType);
    }

    @Override
    public Resource save(FOLDER folder, InputStream inputStream, String contentType) throws Exception {
        String fileName;
        if (folder != null)
            fileName = folder.name().toLowerCase() + "/" + namingStrategy.getName("");
        else
            fileName = namingStrategy.getName("");

        Blob blob = bucket.create(fileName, inputStream, contentType);
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        Metadata metadata = Metadata.builder()
                .size(blob.getSize())
                .contentType(blob.getContentType())
                .bucket(blob.getBucket())
                .etag(blob.getEtag())
                .build();

        Resource resource = Resource.builder()
                .name(blob.getName())
                .url(getURL(storageBucket, blob.getName()))
                .createdAt(Instant.ofEpochMilli(blob.getCreateTime()))
                .updatedAt(Instant.ofEpochSecond(blob.getUpdateTime()))
                .metadata(metadata)
                .folder(folder)
                .build();
        return resourceRepository.save(resource);
    }

    @Override
    public boolean delete(String fileUrl) {
        return false;
    }


    private String getURL(String bucketName, String blobName) {
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, blobName);
    }
}
