package com.spring.fileservice.model;

import com.spring.fileservice.util.constraint.FOLDER;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
@Document(collection = "files")
public class Resource {
    @MongoId
    String id;
    String url;
    String name;
    FOLDER folder;
    Metadata metadata;
    Instant createdAt;
    Instant updatedAt;
    boolean deleted = false;
    Instant deletedAt;
}

