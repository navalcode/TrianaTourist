package com.salesianos.triana.dam.TrianaTourist.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Route implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "route_id",
            foreignKey = @ForeignKey(name="FK_ROUTE_POI")),
            inverseJoinColumns = @JoinColumn(name = "poi_id",
                    foreignKey = @ForeignKey(name="FK_POI_ROUTE")),
            name = "steps"
    )
    private List<Poi> steps= new ArrayList<>();
}
