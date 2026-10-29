package com.salesianos.triana.dam.TrianaTourist.dto.Route;

import com.salesianos.triana.dam.TrianaTourist.validacion.route.name.RouteNameUnique;
import com.salesianos.triana.dam.TrianaTourist.validacion.route.poi.NotDuplicatedPoi;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@NotDuplicatedPoi(nameField = "name", idsField = "ids", message = "La ruta no puede tener dos puntos de interes iguales")
public class CreateRouteDto {
    @javax.validation.constraints.NotBlank(message = "El nombre no puede estar en blanco")
    private String name;
    @Builder.Default
    @javax.validation.constraints.NotNull
    private List<@javax.validation.constraints.NotNull Long> Ids = new ArrayList<>();
}
