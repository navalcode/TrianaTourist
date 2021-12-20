package com.salesianos.triana.dam.TrianaTourist.services;

import com.salesianos.triana.dam.TrianaTourist.dto.category.CategoryDtoConverter;
import com.salesianos.triana.dam.TrianaTourist.dto.poi.CreatePoiDto;
import com.salesianos.triana.dam.TrianaTourist.dto.poi.PoiDto;
import com.salesianos.triana.dam.TrianaTourist.dto.poi.PoiDtoConverter;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.ListEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.SingleEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.models.Category;
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
    private final CategoryService categoryService;
    private final CategoryDtoConverter categoryDtoConverter;

    public List<PoiDto> findAll(){
        return poiRepository.findAll().stream().map(poiDtoConverter::toDto).collect(Collectors.toList());
    }

    public PoiDto findById(Long id){
        Poi data = poiRepository.findById(id).orElse(null);
        if (data == null){
            throw new SingleEntityNotFoundException(id,Poi.class);
        }else
            return poiDtoConverter.toDto(data);
    }

    public PoiDto save (CreatePoiDto dto){
    return poiDtoConverter.toDto(poiRepository.save(Poi.builder()
            .name(dto.getName())
            .location(dto.getLocation())
            .date(dto.getDate())
            .category(categoryService.findCategoryById(dto.getCat_id()))
            .coverPhoto(dto.getCoverPhoto())
            .photo2(dto.getPhoto2())
            .photo3(dto.getPhoto3())
            .build()
    ));
    }

    public PoiDto edit(CreatePoiDto dto, Long id){
        Poi poi = poiRepository.findById(id).orElse(null);
        if(poi == null){
            throw new SingleEntityNotFoundException(id,Poi.class);
        }
        Category category = categoryService.findCategoryById(id);
        poi.setName(dto.getName());
        poi.setCategory(category);
        poi.setCoverPhoto(dto.getCoverPhoto());
        poi.setLocation(dto.getLocation());
        poi.setDate(dto.getDate());
        poi.setPhoto2(dto.getPhoto2());
        poi.setPhoto3(dto.getPhoto3());
        return poiDtoConverter.toDto(poiRepository.save(poi));
    }

    //Este método se usa para que el que RouteService pueda extraer todos los pois a la hora
    //de crear o editar una ruta.
    public Poi findByIdToCreatePoi(Long id){
        Poi data = poiRepository.findById(id).orElse(null);
        if (data == null) {
            throw new SingleEntityNotFoundException(id,Poi.class);

        }else
            return data;
    }

}
