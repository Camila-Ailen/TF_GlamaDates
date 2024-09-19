package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_categoria", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_categoria", length = 100, nullable = false)
    private String nombre;

    @Basic
    @Column(name = "observaciones_categoria", length = 100, nullable = true)
    private String observaciones;

    //RELACIONES
    /*
    conectar con categoria padre, imagen, estacion, producto y servicio
     */
}
