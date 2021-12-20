package com.salesianos.triana.dam.TrianaTourist.validacion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.concurrent.ConcurrentMap;

public class UbicacionPatternValidator implements ConstraintValidator<UbicacionPattern, String> {
    private String LATITUDE_PATTERN="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    private String LONGITUDE_PATTERN="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";

    @Override
    public void initialize(UbicacionPattern constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        String latitud = s.substring(0, s.indexOf(","));
        String longitud = s.substring(s.indexOf(",") + 1);

        if(latitud.matches(LATITUDE_PATTERN) && longitud.matches(LONGITUDE_PATTERN)){
            return true;
        }else{
            return false;
        }
    }
}
