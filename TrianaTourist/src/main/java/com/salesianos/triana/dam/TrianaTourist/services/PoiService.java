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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PoiService {

    private final PoiRepository poiRepository;
    private final PoiDtoConverter poiDtoConverter;

    public List<PoiDto> findAll(){
        return poiRepository.findAll().stream().map(poiDtoConverter::toDto).collect(Collectors.toList());
    }

    //Este método se usa para que el conv
    public Poi findByIdToCreatePoi(Long id){
        Poi data = poiRepository.findById(id).orElse(null);
        if (data == null) {
            throw new ListEntityNotFoundException(Poi.class);

        }else
            return data;
    }

}
