package com.spring.delivery.controller;

import com.spring.delivery.domain.response.file.ResponseFileUpload;
import com.spring.delivery.mapper.ResourceMapper;
import com.spring.delivery.document.Resource;
import com.spring.delivery.service.business.file.FileService;
import com.spring.delivery.service.firebase.FirebaseService;
import com.spring.delivery.util.anotation.ApiMessage;
import com.spring.delivery.util.anotation.EnumValid;
import com.spring.delivery.util.enums.Folder;
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
    ResourceMapper resourceMapper;

    @PostMapping("/{type}/upload")
    @ApiMessage("Upload a file")
    public ResponseEntity<ResponseFileUpload> upload(@PathVariable("type") @EnumValid(enumClass = Folder.class) String type, @RequestParam("file") MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            Folder folder = Folder.valueOf(type.toUpperCase());
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
