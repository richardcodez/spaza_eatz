package com.richardcodez.spaza_eatz.service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.richardcodez.spaza_eatz.exceptions.ResourceNotFoundException;
import com.richardcodez.spaza_eatz.model.Category;
import com.richardcodez.spaza_eatz.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategroyService implements ICategroyService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCategoryById(Long Id) {
        categoryRepository.findById(Id)
            .ifPresentOrElse(categoryRepository::delete, 
            () -> { throw new ResourceNotFoundException("Category not found");});
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Category not found") );
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category updatecategory(Category category, Long Id) {
        return null;
    }

}
