package com.ecommerce.API.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductByPublicId(String publicId) {
        return productRepository.findProductByPublicId(publicId);
    }

    public List<Product> getProductsByKeyword(String keyword) {
        Pattern keywordPattern = Pattern.compile("\\b" + Pattern.quote(keyword) + "\\b", Pattern.CASE_INSENSITIVE);
        List<Product> productsByName = productRepository.findAll().stream().
                filter(p -> keywordPattern.
                        matcher(p.getName()).find()).toList();
        List<Product> productsByDescription = productRepository.findAll().stream().filter(p -> keywordPattern.matcher(p.getDescription()).find() && !productsByName.contains(p)).toList();
        return Stream.concat(productsByName.stream(), productsByDescription.stream()).toList();
    }
}
