package com.salesianos.triana.dam.TrianaTourist.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Route {
    @Id
    private int id;
    private String name;
    @ManyToMany
    private List<Poi> steps;
}
