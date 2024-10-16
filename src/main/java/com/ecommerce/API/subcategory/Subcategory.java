package com.ecommerce.API.subcategory;

import jakarta.persistence.*;

@Entity
@Table(name="subcategory")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="category_id")
    private long categoryId;
    private String name;

    public Subcategory() {
    }

    public Subcategory(long id, long categoryId, String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }
}
