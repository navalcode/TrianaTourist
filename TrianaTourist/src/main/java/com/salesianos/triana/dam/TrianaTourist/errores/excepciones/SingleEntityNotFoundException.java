package com.salesianos.triana.dam.TrianaTourist.errores.excepciones;

public class SingleEntityNotFoundException extends EntityNotFoundException{

    public SingleEntityNotFoundException(Long id, Class clazz) {
        super(String.format("No se ha encontrado la entidad con id %s de la clase %s", id, clazz.getSimpleName()));
    }
}
