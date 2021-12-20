package com.salesianos.triana.dam.TrianaTourist.validacion.route.name;

import com.salesianos.triana.dam.TrianaTourist.repositories.RouteRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@AllArgsConstructor
public class RouteNameUniqueValidator implements ConstraintValidator<RouteNameUnique, String> {
    private final RouteRepository routeRepository;
    @Override
    public void initialize(RouteNameUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !routeRepository.existsByName(value);
    }
}
