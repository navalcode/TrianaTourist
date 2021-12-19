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
    public CategoryDto getCategoryById(@PathVariable @Min(value = 0, message = "No se pueden buscar categorias con un identificador negativo") Long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/")
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable @Min(value = 0, message = "No se pueden actualizar categorias con un identificador negativo") Long id, @RequestBody @Valid CreateCategoryDto categoryDto) {
        return categoryService.update(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable @Min(value = 0, message = "No se pueden eliminar categorias con un identificador negativo") Long id) {
        categoryService.deleteById(id);
    }
}
