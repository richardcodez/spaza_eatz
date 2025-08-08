package com.richardcodez.spaza_eatz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richardcodez.spaza_eatz.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}