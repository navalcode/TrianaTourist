package com.salesianos.triana.dam.TrianaTourist.dto.category;

import com.salesianos.triana.dam.TrianaTourist.validacion.UniqueName;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateCategoryDto {
    @NotEmpty(message = "El nombre no puede estar vacio")
    @NotNull(message = "El nombre no puede ser nulo")
    @UniqueName(message = "La categoría ya existe")
    private String name;
}
