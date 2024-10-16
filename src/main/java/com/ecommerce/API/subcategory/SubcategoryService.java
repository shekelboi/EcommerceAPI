package com.ecommerce.API.subcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public List<Subcategory> getSubcategories() {
        return subcategoryRepository.findAll();
    }

    public Optional<Subcategory> getSubcategory(long id) {
        return subcategoryRepository.findAll().stream().filter(s -> s.getId() == id).findFirst();
    }

    public boolean addSubcategory(Subcategory subcategory) {
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
