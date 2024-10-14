package com.ecommerce.API.subcategory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubcategoryController {
    @GetMapping("/subcategories")
    public List<Subcategory> getSubCategories() {
        return List.of(new Subcategory(1, 1, "TEST"));
    }
}
