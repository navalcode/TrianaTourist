package com.salesianos.triana.dam.TrianaTourist.errores.modelo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiValidationSubError extends ApiSubError{

    private String objeto;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String campo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object valorRechazado;
    private String mensaje;
}
