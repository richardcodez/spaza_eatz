package com.richardcodez.spaza_eatz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richardcodez.spaza_eatz.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}
