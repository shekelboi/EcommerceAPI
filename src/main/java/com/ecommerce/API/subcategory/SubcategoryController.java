package com.ecommerce.API.subcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("/subcategories")
    public List<Subcategory> getSubCategories() {
        return subcategoryService.getSubcategories();
    }

    @GetMapping("/subcategory/{id}")
    public ResponseEntity<Subcategory> getSubCategory(@PathVariable long id) {
        Optional<Subcategory> subcategory = subcategoryService.getSubcategory(id);
        return subcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/subcategory/{id}")
    public boolean deleteSubCategoryById(@PathVariable long id) {
        return subcategoryService.deleteSubcategory(id);
    }

    @DeleteMapping("/subcategory/")
    public boolean deleteSubCategory(@RequestBody Subcategory subcategory) {
        return subcategoryService.deleteSubcategory(subcategory);
    }
}
