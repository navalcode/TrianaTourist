package com.salesianos.triana.dam.TrianaTourist.validacion.route.poi;

import org.springframework.beans.PropertyAccessorFactory;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;

public class NotDuplicatedPoiValidator implements ConstraintValidator<NotDuplicatedPoi, Object> {
    private String idsField;

    @Override
    public void initialize(NotDuplicatedPoi annotation) {
        idsField = annotation.idsField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        List<?> ids = (List<?>) PropertyAccessorFactory.forBeanPropertyAccess(value).getPropertyValue(idsField);
        // Required fields/elements are reported by their own constraints.
        return ids == null || new HashSet<>(ids).size() == ids.size();
    }
}
