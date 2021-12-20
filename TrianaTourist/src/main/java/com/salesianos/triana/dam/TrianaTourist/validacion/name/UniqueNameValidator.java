package com.salesianos.triana.dam.TrianaTourist.validacion.name;

import com.salesianos.triana.dam.TrianaTourist.services.CategoryService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {
  private final CategoryService categoryService;

    @Override
    public void initialize(UniqueName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !categoryService.existsByName(value);
    }
}
