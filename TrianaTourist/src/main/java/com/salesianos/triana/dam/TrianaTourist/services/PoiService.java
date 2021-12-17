package com.salesianos.triana.dam.TrianaTourist.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salesianos.triana.dam.TrianaTourist.repositories.PoiRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoiService {

    private final PoiRepository poiRepository;

    public List<?> findAll(){
        return poiRepository.findAll();
    }
}
