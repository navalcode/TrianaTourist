package com.salesianos.triana.dam.TrianaTourist.validacion.category;

import com.salesianos.triana.dam.TrianaTourist.services.CategoryService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class CategoryExistsValidator implements ConstraintValidator<CategoryExists, Long> {
    private final CategoryService categoryService;

    @Override
    public void initialize(CategoryExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return categoryService.existsById(value);
    }
}
