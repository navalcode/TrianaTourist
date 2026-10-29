package com.salesianos.triana.dam.TrianaTourist.validacion.ubicacion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UbicacionPatternValidator implements ConstraintValidator<UbicacionPattern, String> {
    private String LATITUDE_PATTERN="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    private String LONGITUDE_PATTERN="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";

    @Override
    public void initialize(UbicacionPattern constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        if (s == null) return false;
        String[] coordinates = s.split(",", -1);
        return coordinates.length == 2 && coordinates[0].matches(LATITUDE_PATTERN)
                && coordinates[1].matches(LONGITUDE_PATTERN);
    }
}
