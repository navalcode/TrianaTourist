package com.salesianos.triana.dam.TrianaTourist.dto.Route;

import com.salesianos.triana.dam.TrianaTourist.dto.poi.PoiDto;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RouteDto {
    private Long id;
    private String name;
    private List<Long> steps;
}
