package com.example.bookcrud.repository;

import com.example.bookcrud.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
