package com.ecommerce.API.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id) {
        Optional<Category> subcategory = categoryService.getCategory(id);
        return subcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @RequestMapping(path="/category/", method = {RequestMethod.POST, RequestMethod.PUT})
    public boolean addOrUpdateCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/category/{id}")
    public boolean deleteCategoryById(@PathVariable long id) {
        return categoryService.deleteCategory(id);
    }

    @DeleteMapping("/category/")
    public boolean deleteCategory(@RequestBody Category category) {
        return categoryService.deleteCategory(category);
    }
}
