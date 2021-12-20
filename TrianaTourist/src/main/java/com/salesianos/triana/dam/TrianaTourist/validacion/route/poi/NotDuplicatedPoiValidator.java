package com.salesianos.triana.dam.TrianaTourist.validacion.route.poi;

import com.salesianos.triana.dam.TrianaTourist.models.Poi;
import com.salesianos.triana.dam.TrianaTourist.models.Route;
import com.salesianos.triana.dam.TrianaTourist.repositories.RouteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class NotDuplicatedPoiValidator implements ConstraintValidator<NotDuplicatedPoi, Object> {
    @Autowired
    private RouteRepository routeRepository;
    private String nameField;
    private String idsField;

    @Override
    public void initialize(NotDuplicatedPoi constraintAnnotation) {
    this.nameField = constraintAnnotation.nameField();
    this.idsField = constraintAnnotation.idsField();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //Extraemos el valor de los campos del dto, tanto el contenido del nombre como la lista de ids.
        String name = PropertyAccessorFactory.forBeanPropertyAccess(value).getPropertyValue(nameField).toString();
        List<Long> ids = (List<Long>) PropertyAccessorFactory.forBeanPropertyAccess(value).getPropertyValue(idsField);
        //Recuperamos del repositorio la ruta que tenga ese nombre.
        Route route = routeRepository.findByName(name);
        if  (route == null) {
            //check all elements in ids are unique
            Set<Long> set = new HashSet<Long>(ids);

            if(set.size() < ids.size()){
                return false;
            }else{
                return true;
            }
        }else {
            //Extraemos los pois de la ruta.
            List<Poi> pois = route.getSteps();
            //Recorremos la lista de ids y comprobamos que no esten duplicados.
            CollectionUtils.containsAny(ids, pois);

            return !CollectionUtils.containsAny(ids, pois);
        }
    }

}
