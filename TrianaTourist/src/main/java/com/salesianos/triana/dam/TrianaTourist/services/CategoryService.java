package com.salesianos.triana.dam.TrianaTourist.services;

import com.salesianos.triana.dam.TrianaTourist.dto.category.CategoryDto;
import com.salesianos.triana.dam.TrianaTourist.dto.category.CategoryDtoConverter;

import com.salesianos.triana.dam.TrianaTourist.dto.category.CreateCategoryDto;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.ListEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.SingleEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.salesianos.triana.dam.TrianaTourist.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;

    public List<CategoryDto> findAll() {
        List<Category>data = categoryRepository.findAll();
        if (data.isEmpty()) {
             throw new ListEntityNotFoundException(Category.class);
        }else
            return data.stream().map(categoryDtoConverter::convertToDto).collect(java.util.stream.Collectors.toList());
    }

    public CategoryDto findById(Long id) {
        Category data = categoryRepository.findById(id).orElse(null);
        if (data == null) {
            throw new SingleEntityNotFoundException(id,Category.class);
        }else
            return categoryDtoConverter.convertToDto(data);
    }

    public CategoryDto save(CreateCategoryDto categoryDto) {
        Category category = categoryDtoConverter.convertToEntity(categoryDto);
        return categoryDtoConverter.convertToDto(categoryRepository.save(category));
    }
}
