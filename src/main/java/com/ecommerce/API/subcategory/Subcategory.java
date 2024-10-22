package com.ecommerce.API.subcategory;

import com.ecommerce.API.category.Category;
import com.ecommerce.API.product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "subcategory")
public class Subcategory {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    Category category;

    @OneToMany(mappedBy = "subcategory")
    @JsonManagedReference
    List<Product> products = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public Subcategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
