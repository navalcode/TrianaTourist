package com.salesianos.triana.dam.TrianaTourist.dto.Route;

import com.salesianos.triana.dam.TrianaTourist.dto.poi.PoiDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateRouteDto {
    private String name;
    private List<Long> Ids= new ArrayList<>();
}
