package com.salesianos.triana.dam.TrianaTourist.controllers;

import com.salesianos.triana.dam.TrianaTourist.dto.category.CategoryDto;
import com.salesianos.triana.dam.TrianaTourist.dto.category.CreateCategoryDto;
import com.salesianos.triana.dam.TrianaTourist.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable @Min(value = 0, message = "No se pueden buscar estaciones con un identificador negativo") Long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/")
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }
}