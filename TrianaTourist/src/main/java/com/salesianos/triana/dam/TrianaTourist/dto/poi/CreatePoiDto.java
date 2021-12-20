package com.salesianos.triana.dam.TrianaTourist.dto.poi;

import com.salesianos.triana.dam.TrianaTourist.validacion.category.CategoryExists;
import com.salesianos.triana.dam.TrianaTourist.validacion.ubicacion.UbicacionPattern;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreatePoiDto {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;
    @UbicacionPattern
    private String location;
    private String date;
    @CategoryExists
    private Long cat_id;
    @NotNull(message = "CoverPhoto no puede ser nula")
    @URL(message = "La url no es valida")
    private String coverPhoto;
    @URL(message = "La url no es valida")
    private String photo2;
    @URL(message = "La url no es valida")
    private String photo3;
}
