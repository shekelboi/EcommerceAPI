package com.ecommerce.API.product;

import com.ecommerce.API.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    @GetMapping("/product/{publicId}/")
    public ResponseEntity<Product> getProductByPublicId(@PathVariable String publicId) {
        Optional<Product> product = productRepository.findProductByPublicId(publicId);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
