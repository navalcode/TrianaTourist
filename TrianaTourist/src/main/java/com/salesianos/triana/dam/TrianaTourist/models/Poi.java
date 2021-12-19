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
public class Poi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String date;
    @ManyToOne
    @JoinColumn(name = "category_id",foreignKey = @ForeignKey(name = "FK_POI_CATEGORY"))
    private Category category;
    private String coverPhoto;
    private String photo2;
    private String photo3;

}
