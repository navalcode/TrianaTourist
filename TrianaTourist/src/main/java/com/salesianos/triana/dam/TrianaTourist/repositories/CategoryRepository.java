package com.salesianos.triana.dam.TrianaTourist.repositories;


import com.salesianos.triana.dam.TrianaTourist.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);
}
