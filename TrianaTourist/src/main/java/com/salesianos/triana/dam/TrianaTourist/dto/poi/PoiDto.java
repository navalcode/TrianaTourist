package com.salesianos.triana.dam.TrianaTourist.dto.poi;

import com.salesianos.triana.dam.TrianaTourist.models.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PoiDto {
    private Long id;
    private String name;
    private String location;
    private String date;
    private Category category;
    private String coverPhoto;
    private String photo2;
    private String photo3;
}
