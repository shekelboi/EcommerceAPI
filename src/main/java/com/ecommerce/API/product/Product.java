package com.ecommerce.API.product;

import com.ecommerce.API.category.Category;
import com.ecommerce.API.subcategory.Subcategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "public_id")
    private String publicId;

    private String name;

    private String description;

    private String slug;

    @Column(name = "image_id")
    private String imageId;

    private Double price;

    private int stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subcategory_id")
    @JsonBackReference
    private Subcategory subcategory;

    @JsonProperty(value = "category", access = JsonProperty.Access.READ_ONLY)
    private Category getCategory() {
        return subcategory.getCategory();
    }
}
