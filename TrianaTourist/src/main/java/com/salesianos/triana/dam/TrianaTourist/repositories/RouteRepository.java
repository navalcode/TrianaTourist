package com.salesianos.triana.dam.TrianaTourist.repositories;

import com.salesianos.triana.dam.TrianaTourist.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route,Long> {

    @Query(
            "SELECT r FROM Route r LEFT JOIN r.steps s WHERE s.id = :id"
    )
    List<Route> findAllByIdPoi(Long id);
}
