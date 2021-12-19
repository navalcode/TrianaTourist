package com.salesianos.triana.dam.TrianaTourist.controllers;

import com.salesianos.triana.dam.TrianaTourist.dto.Route.CreateRouteDto;
import com.salesianos.triana.dam.TrianaTourist.dto.Route.RouteDto;
import com.salesianos.triana.dam.TrianaTourist.services.RouteService;
import lombok.RequiredArgsConstructor;
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
    public RouteDto getRouteById(@PathVariable @Min(value = 0, message = "No se pueden buscar categorias con un identificador negativo") Long id) {
        return routeService.findById(id);
    }

    @PostMapping("/")
    public RouteDto saveRoute(@RequestBody CreateRouteDto dto) {
        return routeService.save(dto);
    }
}
