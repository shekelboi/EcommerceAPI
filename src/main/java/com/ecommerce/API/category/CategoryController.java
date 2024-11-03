package com.ecommerce.API.category;

import com.ecommerce.API.product.Product;
import com.ecommerce.API.response.BooleanResponse;
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

    @GetMapping("/api/public/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/api/public/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id) {
        Optional<Category> subcategory = categoryService.getCategory(id);
        return subcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/api/public/category/{id}/products")
    public List<Product> getProductsInCategory(@PathVariable Long id) {
        return categoryService.getProductsInCategory(id);
    }

    @RequestMapping(path = "/api/admin/category/", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<BooleanResponse> addOrUpdateCategory(@RequestBody Category category) {
        return ResponseEntity.ok(new BooleanResponse(categoryService.addCategory(category)));
    }

    @DeleteMapping("/api/admin/category/{id}")
    public ResponseEntity<BooleanResponse> deleteCategoryById(@PathVariable long id) {
        return ResponseEntity.ok(new BooleanResponse(categoryService.deleteCategory(id)));
    }

    @DeleteMapping("/api/admin/category/")
    public ResponseEntity<BooleanResponse> deleteCategory(@RequestBody Category category) {
        return ResponseEntity.ok(new BooleanResponse(categoryService.deleteCategory(category)));
    }
}
