package com.salesianos.triana.dam.TrianaTourist.dto.poi;

import com.salesianos.triana.dam.TrianaTourist.models.Poi;
import org.springframework.stereotype.Component;

@Component
public class PoiDtoConverter {
    public PoiDto toDto(Poi poi) {
        return PoiDto.builder()
                .id(poi.getId())
                .name(poi.getName())
                .location(poi.getLocation())
                .date(poi.getDate())
                .category(poi.getCategory())
                .coverPhoto(poi.getCoverPhoto())
                .photo2(poi.getPhoto2())
                .photo3(poi.getPhoto3())
                .build();
    }
}
