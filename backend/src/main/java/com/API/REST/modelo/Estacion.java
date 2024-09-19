package com.API.REST.modelo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "estacion")
@Getter
@Setter
@NoArgsConstructor
public class Estacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_estacion", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "descripcion_estacion", length = 100, nullable = false)
    private String descripcion;

    //ENUMS
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_estacion", nullable = false)
    private EstacionEstado estado;
}
