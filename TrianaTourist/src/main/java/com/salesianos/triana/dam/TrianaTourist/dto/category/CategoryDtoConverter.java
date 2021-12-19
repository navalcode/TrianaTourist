package com.salesianos.triana.dam.TrianaTourist.dto.category;

import com.salesianos.triana.dam.TrianaTourist.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoConverter {

   //Convierte una categoria a un dto usando por medio de su builder.
    public CategoryDto convertToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category convertToEntity(CreateCategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .build();
    }
}
