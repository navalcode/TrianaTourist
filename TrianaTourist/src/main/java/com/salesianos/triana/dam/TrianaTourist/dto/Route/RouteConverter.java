package com.salesianos.triana.dam.TrianaTourist.dto.Route;

import com.salesianos.triana.dam.TrianaTourist.dto.poi.PoiDtoConverter;
import com.salesianos.triana.dam.TrianaTourist.models.Category;
import com.salesianos.triana.dam.TrianaTourist.models.Poi;
import com.salesianos.triana.dam.TrianaTourist.models.Route;
import com.salesianos.triana.dam.TrianaTourist.services.CategoryService;
import com.salesianos.triana.dam.TrianaTourist.services.PoiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RouteConverter {

    public RouteDto toDto(Route route){
        List<Long> pois= new ArrayList<>();
        route.getSteps().stream().forEach(x-> pois.add(x.getId()));

        return RouteDto.builder()
                .id(route.getId())
                .name(route.getName())
                .steps(pois)
                .build();
    }

}
