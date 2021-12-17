package com.salesianos.triana.dam.TrianaTourist.models;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Poi {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String location;
    private String date;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String coverPhoto;
    private String photo2;
    private String photo3;


}
