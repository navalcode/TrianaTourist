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
@NotDuplicatedPoi(nameField = "name", idsField = "Ids", message = "La ruta no puede tener dos puntos de interes iguales")
public class CreateRouteDto {
    @RouteNameUnique
    private String name;
    private List<Long> Ids= new ArrayList<>();
}
