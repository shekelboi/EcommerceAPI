package com.ecommerce.API.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/api/public/products")
    public List<Product> getProducts(@RequestParam(required = false) String keyword) {
        if (keyword != null) {
            return productService.getProductsByKeyword(keyword);
        } else {
            return productService.getAllProducts();
        }
    }

    @GetMapping("/api/public/product/{publicId}")
    public ResponseEntity<Product> getProductByPublicId(@PathVariable String publicId) {
        return productService.getProductByPublicId(publicId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
