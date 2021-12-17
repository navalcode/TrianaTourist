package com.salesianos.triana.dam.TrianaTourist.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salesianos.triana.dam.TrianaTourist.repositories.RouteRepository;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

}
