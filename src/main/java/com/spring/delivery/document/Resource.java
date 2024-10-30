package com.spring.delivery.document;

import com.spring.delivery.util.enums.Folder;
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
    Folder folder;
    Metadata metadata;
    Instant createdAt;
    Instant updatedAt;
    boolean deleted = false;
    Instant deletedAt;
}

