package com.spring.fileservice.controller;

import com.spring.fileservice.domain.response.ResponseFileUpload;
import com.spring.fileservice.mapper.ResourceMapper;
import com.spring.fileservice.model.Resource;
import com.spring.fileservice.service.FileService;
import com.spring.fileservice.service.FirebaseService;
import com.spring.fileservice.util.anotation.ApiMessage;
import com.spring.fileservice.util.anotation.EnumValid;
import com.spring.fileservice.util.constraint.FOLDER;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileController {
    FirebaseService firebaseService;
    FileService fileService;
    ResourceMapper resourceMapper = ResourceMapper.INSTANCE;

    @PostMapping("/{type}/upload")
    @ApiMessage("Upload a file")
    public ResponseEntity<ResponseFileUpload> upload(@PathVariable("type") @EnumValid(enumClass = FOLDER.class) String type, @RequestParam("file") MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            FOLDER folder = FOLDER.valueOf(type.toUpperCase());
            Resource resource = firebaseService.save(folder, inputStream, contentType);
            return ResponseEntity.ok(resourceMapper.toResponseFileUpload(resource));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @ApiMessage("Get URL")
    public ResponseEntity<ResponseFileUpload> getUrl(@PathVariable("id") String id) {
        Resource resource = fileService.findById(id);
        if (resource == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(resourceMapper.toResponseFileUpload(resource));
    }
}
