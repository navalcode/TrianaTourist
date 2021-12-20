package com.salesianos.triana.dam.TrianaTourist.validacion.category;

import com.salesianos.triana.dam.TrianaTourist.validacion.ubicacion.UbicacionPatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryExistsValidator.class)
public @interface CategoryExists {
    String message() default "La categoría debe existir";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
