package com.salesianos.triana.dam.TrianaTourist.validacion.ubicacion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UbicacionPatternValidator.class)
public @interface UbicacionPattern {
    String message() default "La ubicacion no es correcta, debe seguir el esquema: LL.LLLLLL,LL.LLLLLL";

    Class <?> [] groups() default{};
    Class <? extends Payload> [] payload() default {};
}
