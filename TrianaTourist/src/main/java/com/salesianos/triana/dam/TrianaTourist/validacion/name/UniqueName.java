package com.salesianos.triana.dam.TrianaTourist.validacion.name;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameValidator.class)
public @interface UniqueName {
    String message() default "El nombre de la categoría no puede estar repetida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
