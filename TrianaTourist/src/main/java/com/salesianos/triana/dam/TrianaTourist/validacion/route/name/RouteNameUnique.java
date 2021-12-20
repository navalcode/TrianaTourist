package com.salesianos.triana.dam.TrianaTourist.validacion.route.name;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RouteNameUniqueValidator.class)
public @interface RouteNameUnique {
    String message() default "La ruta no puede tener el mismo nombre que otra ruta existente";

    Class <?> [] groups() default{};
    Class <? extends Payload> [] payload() default {};
}
