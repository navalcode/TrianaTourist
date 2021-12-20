package com.salesianos.triana.dam.TrianaTourist.controllers;

import com.salesianos.triana.dam.TrianaTourist.dto.Route.CreateRouteDto;
import com.salesianos.triana.dam.TrianaTourist.dto.Route.RouteDto;
import com.salesianos.triana.dam.TrianaTourist.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route")
@Validated
public class RouteController {
    private final RouteService routeService;

    @GetMapping("/")
    public List<RouteDto> getAllRoutes() {
        return routeService.findAll();
    }

    @GetMapping("/{id}")
    public RouteDto getRouteById(@PathVariable @Min(value = 0, message = "No se pueden buscar rutas con un identificador negativo") Long id) {
        return routeService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<RouteDto> saveRoute(@RequestBody CreateRouteDto dto) {
       return ResponseEntity.status(HttpStatus.CREATED).body(routeService.save(dto));
    }

    @PutMapping("/{id}")
    public RouteDto editRoute(@PathVariable @Min(value = 0,message = "No se pueden buscar rutas con un identificador negativo") Long id, @RequestBody CreateRouteDto dto){
        return routeService.edit(id,dto);
    }
    @PutMapping("/{id}/{id2}")
    public RouteDto editRoute(@PathVariable @Min(value = 0,message = "No se pueden buscar rutas con un identificador negativo") Long id, @PathVariable @Min(value = 0,message = "No se pueden buscar Pois con un identificador negativo") Long id2){
        return routeService.addPoiToRoute(id,id2);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable @Min(value = 0, message = "No se puede eliminar una ruta con identificador negativo") Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(routeService.deleteById(id));
    }

}
