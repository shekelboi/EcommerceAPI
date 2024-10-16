package com.ecommerce.API.subcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("/subcategories")
    public List<Subcategory> getSubcategories() {
        return subcategoryService.getSubcategories();
    }

    @GetMapping("/subcategory/{id}")
    public ResponseEntity<Subcategory> getSubcategory(@PathVariable long id) {
        Optional<Subcategory> subcategory = subcategoryService.getSubcategory(id);
        return subcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/subcategory/")
    @PutMapping("/subcategory/")
    public boolean addOrUpdateSubcategory(@RequestBody Subcategory subcategory) {
        return subcategoryService.addSubcategory(subcategory);
    }

    @DeleteMapping("/subcategory/{id}")
    public boolean deleteSubcategoryById(@PathVariable long id) {
        return subcategoryService.deleteSubcategory(id);
    }

    @DeleteMapping("/subcategory/")
    public boolean deleteSubcategory(@RequestBody Subcategory subcategory) {
        return subcategoryService.deleteSubcategory(subcategory);
    }
}
