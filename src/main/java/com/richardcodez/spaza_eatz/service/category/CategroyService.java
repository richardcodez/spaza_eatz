package com.richardcodez.spaza_eatz.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.richardcodez.spaza_eatz.exceptions.AlreadyExistsException;
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
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save).orElseThrow(() -> new AlreadyExistsException(category.getName() + "Already exists"));
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
        return Optional.ofNullable(getCategoryById(Id)).map(oldCategory -> {
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

}
