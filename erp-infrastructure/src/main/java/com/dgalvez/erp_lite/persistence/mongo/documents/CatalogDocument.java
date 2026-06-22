package com.dgalvez.erp_lite.persistence.mongo.documents;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "catalogs")
public class CatalogDocument {

    @Id
    private String id;

    private String name;

    private String description;

    private CatalogType catalogType;

    @Field(name = "active")
    private Boolean active;

    private List<CatalogItem> items;

    private Instant createdAt;

    private Instant updatedAt;
}