package com.dgalvez.erp_lite.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_products_sku",
                        columnNames = "sku"
                )
        },
        indexes = {
                @Index(
                        name = "idx_products_category_id",
                        columnList = "category_id"
                ),
                @Index(
                        name = "idx_products_active",
                        columnList = "active"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @UuidGenerator
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private UUID id;

    @Column(
            name = "sku",
            nullable = false,
            unique = true,
            length = 50
    )
    private String sku;

    @Column(
            name = "name",
            nullable = false,
            length = 200
    )
    private String name;

    @Column(name = "description")
    private String description;

    @Column(
            name = "price",
            nullable = false,
            precision = 15,
            scale = 2
    )
    private BigDecimal price;

    @Column(
            name = "stock",
            nullable = false
    )
    private Integer stock;

    @Column(
            name = "category_id",
            length = 100
    )
    private String categoryId;

    @Column(
            name = "image_url",
            length = 500
    )
    private String imageUrl;

    @Column(
            name = "active",
            nullable = false
    )
    private Boolean active;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;

    @Builder.Default
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<OrderProductEntity> orderItems = new ArrayList<>();

    @PrePersist
    void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}