package com.salesianos.triana.dam.TrianaTourist.services;

import com.salesianos.triana.dam.TrianaTourist.dto.Route.CreateRouteDto;
import com.salesianos.triana.dam.TrianaTourist.dto.Route.RouteConverter;
import com.salesianos.triana.dam.TrianaTourist.dto.Route.RouteDto;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.ListEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.SingleEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.models.Route;
import com.salesianos.triana.dam.TrianaTourist.repositories.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salesianos.triana.dam.TrianaTourist.repositories.RouteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final PoiRepository poiRepository;
    private final RouteConverter routeConverter;

    public List<RouteDto> findAll() {
        List<Route> data = routeRepository.findAll();
        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Route.class);
        }else
            return data.stream().map(routeConverter::toDto).collect(java.util.stream.Collectors.toList());
    }

    public RouteDto findById(Long id) {
        Route data = routeRepository.findById(id).orElse(null);
        if(data == null) {
            throw new SingleEntityNotFoundException(id,Route.class);
        }else
            return routeConverter.toDto(data);
    }

    public RouteDto save(CreateRouteDto dto) {
        Route route = routeConverter.toEntity(dto);
       return routeConverter.toDto(routeRepository.save(route));
    }
}
