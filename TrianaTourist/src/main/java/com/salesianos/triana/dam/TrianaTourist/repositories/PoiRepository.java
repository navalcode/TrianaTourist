package com.salesianos.triana.dam.TrianaTourist.repositories;

import com.salesianos.triana.dam.TrianaTourist.models.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoiRepository extends JpaRepository<Poi,Long> {
}
