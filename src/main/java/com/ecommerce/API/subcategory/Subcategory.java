package com.ecommerce.API.subcategory;

import com.ecommerce.API.category.Category;
import com.ecommerce.API.product.Product;
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
@Table(name="subcategory")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    Category category;

    @OneToMany(mappedBy = "subcategory")
    @JsonManagedReference
    Set<Product> products = new HashSet<>();

    public Subcategory(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

}
