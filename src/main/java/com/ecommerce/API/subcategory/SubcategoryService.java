package com.ecommerce.API.subcategory;

import com.ecommerce.API.category.Category;
import com.ecommerce.API.category.CategoryService;
import com.ecommerce.API.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private CategoryService categoryService;

    public List<Subcategory> getSubcategories() {
        return subcategoryRepository.findAll();
    }

    public Optional<Subcategory> getSubcategory(long id) {
        return subcategoryRepository.findById(id);
    }

    public List<Product> getProductsInSubcategory(long id) {
        Optional<Subcategory> subcategory = subcategoryRepository.findById(id);
        return subcategory.map(Subcategory::getProducts).orElse(null);
    }

    public boolean addSubcategory(Long categoryId, Subcategory subcategory) {
        Optional<Category> category = categoryService.getCategory(categoryId);
        if (category.isEmpty()) {
            return false;
        }
        subcategory.setCategory(category.get());
        try {
            subcategoryRepository.save(subcategory);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteSubcategory(long id) {
        Optional<Subcategory> subcategory = getSubcategory(id);
        if (subcategory.isPresent()) {
            try {
                subcategoryRepository.delete(subcategory.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteSubcategory(Subcategory subcategory) {
        Example<Subcategory> subcategoryExample = Example.of(subcategory);
        if (!subcategoryRepository.exists(subcategoryExample)) {
            return false;
        }
        try {
            subcategoryRepository.delete(subcategory);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
