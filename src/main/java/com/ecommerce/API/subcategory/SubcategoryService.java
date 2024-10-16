package com.ecommerce.API.subcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public List<Subcategory> getSubcategories() {
        return this.subcategoryRepository.findAll();
    }

    public Optional<Subcategory> getSubcategory(long id) {
        return this.subcategoryRepository.findAll().stream().filter(s -> s.getId() == id).findFirst();
    }
}
