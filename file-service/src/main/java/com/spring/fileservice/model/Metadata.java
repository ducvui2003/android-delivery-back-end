package com.spring.fileservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Metadata {
    long size;
    String contentType;
    String bucket;
    String etag;
}
