package com.salesianos.triana.dam.TrianaTourist.errores.excepciones;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException (String message){
        super (message);
    }
}
