package com.salesianos.triana.dam.TrianaTourist.validacion;

import com.salesianos.triana.dam.TrianaTourist.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {
  private final CategoryRepository categoryRepository;

    @Override
    public void initialize(UniqueName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !categoryRepository.existsByName(value);
    }
}
