package com.richardcodez.spaza_eatz.service.category;

import java.util.List;

import com.richardcodez.spaza_eatz.model.Category;

public interface ICategroyService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updatecategory(Category category, Long Id);
    void deleteCategoryById(Long Id);
}
