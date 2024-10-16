package com.ecommerce.API.subcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("/subcategories")
    public List<Subcategory> getSubCategories() {
        return this.subcategoryService.getSubcategories();
    }

    @GetMapping("/subcategory/{id}")
    public ResponseEntity<Subcategory> getSubCategories(@PathVariable long id) {
        Optional<Subcategory> subcategory = this.subcategoryService.getSubcategory(id);
        return subcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
