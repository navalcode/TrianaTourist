package com.salesianos.triana.dam.TrianaTourist.errores.excepciones;

public class ListEntityNotFoundException extends EntityNotFoundException{
    public ListEntityNotFoundException(Class clazz) {
        super(String.format("No se ha encontrado ninguna entidad de %s", clazz.getSimpleName()));
    }
}
