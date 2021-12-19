package com.salesianos.triana.dam.TrianaTourist.services;

import com.salesianos.triana.dam.TrianaTourist.dto.poi.PoiDto;
import com.salesianos.triana.dam.TrianaTourist.dto.poi.PoiDtoConverter;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.ListEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.models.Poi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salesianos.triana.dam.TrianaTourist.repositories.PoiRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoiService {

    private final PoiRepository poiRepository;
    private final PoiDtoConverter poiDtoConverter;

    public List<?> findAll(){
        return poiRepository.findAll();
    }

    public Poi findByIdToCreatePoi(Long id){
        Poi data = poiRepository.findById(id).orElse(null);
        if (data == null) {
            throw new ListEntityNotFoundException(Poi.class);

        }else
            return data;
    }

}
