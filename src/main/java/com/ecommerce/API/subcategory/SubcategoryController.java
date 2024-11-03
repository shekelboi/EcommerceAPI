package com.ecommerce.API.subcategory;

import com.ecommerce.API.product.Product;
import com.ecommerce.API.response.BooleanResponse;
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

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @GetMapping("/api/public/subcategories")
    public List<Subcategory> getSubcategories() {
        return subcategoryService.getSubcategories();
    }

    @GetMapping("/api/public/subcategory/{id}")
    public ResponseEntity<Subcategory> getSubcategory(@PathVariable long id) {
        Optional<Subcategory> subcategory = subcategoryService.getSubcategory(id);
        return subcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/api/public/subcategory/{id}/products")
    public List<Product> getProductsInSubcategory(@PathVariable Long id) {
        return subcategoryService.getProductsInSubcategory(id);
    }

    // Different approach
    @RequestMapping(path = "/api/admin/subcategory/", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<BooleanResponse> addOrUpdateSubcategoryDifferent(@RequestBody Subcategory subcategory) {
        try {
            subcategoryRepository.save(subcategory);
            return ResponseEntity.ok(new BooleanResponse(true));
        } catch (Exception e) {
            return ResponseEntity.status(403).body(new BooleanResponse(true));
        }
    }

    @RequestMapping(path = "/api/admin/category/{categoryId}/subcategory/", method = {RequestMethod.POST, RequestMethod.PUT})
    public boolean addOrUpdateSubcategory(@PathVariable long categoryId, @RequestBody Subcategory subcategory) {
        return subcategoryService.addSubcategory(categoryId, subcategory);
    }

    @DeleteMapping("/api/admin/subcategory/{id}")
    public boolean deleteSubcategoryById(@PathVariable long id) {
        return subcategoryService.deleteSubcategory(id);
    }

    @DeleteMapping("/api/admin/subcategory/")
    public boolean deleteSubcategory(@RequestBody Subcategory subcategory) {
        return subcategoryService.deleteSubcategory(subcategory);
    }
}
