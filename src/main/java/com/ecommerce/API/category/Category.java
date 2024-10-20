package com.ecommerce.API.category;

import com.ecommerce.API.subcategory.Subcategory;
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
    @JsonManagedReference
    private final Set<Subcategory> subcategories = new HashSet<>();

    public Category(long categoryId) {
        this.id = categoryId;
    }
}
