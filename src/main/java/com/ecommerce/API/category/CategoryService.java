package com.ecommerce.API.category;

import com.ecommerce.API.product.Product;
import com.ecommerce.API.subcategory.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(long id) {
        return categoryRepository.findAll().stream().filter(s -> s.getId() == id).findFirst();
    }

    public List<Product> getProductsInCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            return new ArrayList<>();
        }
        List<Subcategory> subcategories = category.get().getSubcategories();
        return subcategories.stream().flatMap(s -> s.getProducts().stream()).collect(Collectors.toList());
    }

    public boolean addCategory(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteCategory(long id) {
        Optional<Category> subcategory = getCategory(id);
        if (subcategory.isPresent()) {
            try {
                categoryRepository.delete(subcategory.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteCategory(Category category) {
        Example<Category> subcategoryExample = Example.of(category);
        if (!categoryRepository.exists(subcategoryExample)) {
            return false;
        }
        try {
            categoryRepository.delete(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
