package com.ecommerce.API.product;

import com.ecommerce.API.subcategory.Subcategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "public_id")
    @JsonProperty()
    private String publicId;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String slug;

    @Column(name = "image_id")
    @JsonProperty
    private String imageId;

    @JsonProperty
    private Double price;

    @JsonProperty
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    @JsonBackReference
    private Subcategory subcategory;
}
