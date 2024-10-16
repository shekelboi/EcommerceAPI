package com.ecommerce.API.subcategory;

import com.ecommerce.API.category.Category;
import com.ecommerce.API.category.CategoryService;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Entity
@Table(name="subcategory")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    public Subcategory() {
    }

    public Subcategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    @JsonProperty("categoryId")
    public long getCategoryId() {
        return category.getId();
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }
}
