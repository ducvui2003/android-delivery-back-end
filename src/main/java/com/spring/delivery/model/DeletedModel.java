package com.spring.delivery.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@FilterDef(name = "softDeleteFilter", parameters = @ParamDef(name = "is_deleted", type = Boolean.class))
@Filter(name = "softDeleteFilter", condition = "is_deleted = :isDeleted")
public class DeletedModel extends BaseModel {
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    boolean isDeleted = Boolean.FALSE;
    Instant deletedAt;
    String deletedBy;
}
