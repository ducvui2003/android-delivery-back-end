package com.spring.delivery.controller;

import com.spring.delivery.util.anotation.ApiMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/api/v1/updload")
public class FileUploadController {

    @PostMapping("/")
    @ApiMessage("done")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestBody Body body) {
        System.out.println(body);
        System.out.println(file != null);
        return ResponseEntity.ok("Thanh công");
    }


    record Body(String name, String id) {
    }
}