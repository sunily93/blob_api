package com.example.blob.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blob.entity.Category;

public interface CategoryRepositry extends JpaRepository<Category, Integer> {

}
