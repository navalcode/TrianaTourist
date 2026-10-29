package com.salesianos.triana.dam.TrianaTourist.services;

import com.salesianos.triana.dam.TrianaTourist.dto.Route.CreateRouteDto;
import com.salesianos.triana.dam.TrianaTourist.dto.Route.RouteConverter;
import com.salesianos.triana.dam.TrianaTourist.dto.Route.RouteDto;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.ListEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.SingleEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.models.Category;
import com.salesianos.triana.dam.TrianaTourist.models.Poi;
import com.salesianos.triana.dam.TrianaTourist.models.Route;
import com.salesianos.triana.dam.TrianaTourist.repositories.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.InvalidRequestException;
import com.salesianos.triana.dam.TrianaTourist.repositories.RouteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final PoiRepository poiRepository;
    private final PoiService poiService;
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
        requireUniqueName(dto.getName(), null);
        List<Poi> pois= new ArrayList<>();
        dto.getIds().stream().forEach(x-> pois.add(poiService.findByIdToCreatePoi(x)));

       return routeConverter.toDto(routeRepository.save( Route.builder()
               .name(dto.getName())
               .steps(pois)
               .build()));
    }

    public RouteDto edit(Long id,CreateRouteDto dto){
        Route route = routeRepository.findById(id).orElse(null);
        if (route == null) {
            throw new SingleEntityNotFoundException(id,Route.class);
        }
        requireUniqueName(dto.getName(), id);
        List<Poi> pois= new ArrayList<>();
        dto.getIds().stream().forEach(x-> pois.add(poiService.findByIdToCreatePoi(x)));

        route.setName(dto.getName());
        route.setSteps(pois);
        return routeConverter.toDto(routeRepository.save(route));

    }

    public RouteDto addPoiToRoute(Long id, Long idPoi) {
        Route route = routeRepository.findById(id).orElse(null);
        if (route == null) {
            throw new SingleEntityNotFoundException(id,Route.class);
        }
        Poi poi = poiRepository.findById(idPoi).orElse(null);
        if (poi == null) {
            throw new SingleEntityNotFoundException(idPoi,Poi.class);
        }
        if (route.getSteps().stream().anyMatch(step -> idPoi.equals(step.getId()))) {
            throw new InvalidRequestException("La ruta no puede tener dos puntos de interés iguales");
        }
        route.getSteps().add(poi);
        return routeConverter.toDto(routeRepository.save(route));
    }

    public ResponseEntity<?> deleteById(Long id) {
        if(!routeRepository.existsById(id)){
            throw new SingleEntityNotFoundException(id,Route.class);
        }
        routeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private void requireUniqueName(String name, Long currentId) {
        Route existing = routeRepository.findByName(name);
        if (existing != null && !existing.getId().equals(currentId)) {
            throw new InvalidRequestException("El nombre de ruta ya existe");
        }
    }
}
