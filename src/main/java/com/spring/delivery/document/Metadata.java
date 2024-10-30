package com.spring.delivery.document;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Metadata {
    Long size;
    String contentType;
    String bucket;
    String etag;
}
