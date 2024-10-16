package com.ecommerce.API.category;

import com.ecommerce.API.subcategory.Subcategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String logo;


    @OneToMany(mappedBy = "category")
    private Set<Subcategory> subcategories = new HashSet<>();

    public Category() {

    }

    public Category(long categoryId) {
        this.id = categoryId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }
}
