package com.salesianos.triana.dam.TrianaTourist.controllers;

import com.salesianos.triana.dam.TrianaTourist.dto.poi.CreatePoiDto;
import com.salesianos.triana.dam.TrianaTourist.dto.poi.PoiDto;
import com.salesianos.triana.dam.TrianaTourist.services.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/poi")
@Validated
public class PoiController {
    @Autowired
    private PoiService poiService;

    @GetMapping("/")
    public List<PoiDto> listarTodas(){
        return poiService.findAll();
    }

    @GetMapping("/{id}")
    public PoiDto findById(@PathVariable @Min(value = 0, message = "No se puede buscar un Poi por un identificador negativo")Long id){
        return poiService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<PoiDto> save(@RequestBody  @Valid CreatePoiDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(poiService.save(dto));
    }

    @PutMapping("/{id}")
    public PoiDto edit(@RequestBody @Valid CreatePoiDto dto, @PathVariable @Min(value = 0,message = "No se puede buscar un Poi por un identificador negativo") Long id){
        return poiService.edit(dto,id);
    }
}
