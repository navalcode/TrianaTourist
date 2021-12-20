package com.salesianos.triana.dam.TrianaTourist.dto.poi;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreatePoiDto {
    private String name;
    private String location;
    private String date;
    private Long cat_id;
    private String coverPhoto;
    private String photo2;
    private String photo3;
}
