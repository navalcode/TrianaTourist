package com.salesianos.triana.dam.TrianaTourist.validacion.route.poi;

import com.salesianos.triana.dam.TrianaTourist.validacion.route.name.RouteNameUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Target({ElementType.FIELD,ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotDuplicatedPoiValidator.class)
public @interface NotDuplicatedPoi {
    String message() default "La ruta no puede tener dos puntos de interes iguales";
    Class <?> [] groups() default{};
    Class <? extends Payload> [] payload() default {};

    String nameField();
    String idsField();
}
