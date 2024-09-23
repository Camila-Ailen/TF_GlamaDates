package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "servicio")
@Getter
@Setter
@NoArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_servicio", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_servicio", length = 100, nullable = false)
    private String nombre;

    @Basic
    @Column(name = "descripcion_servicio", length = 100, nullable = false)
    private String descripcion;

    @Basic
    @Column(name = "duracion_servicio", nullable = false)
    private int duracion;

    @Basic
    @Column(name = "precio_servicio", nullable = false)
    private double precio;


    //RELACIONES
    //Muchos servicios pueden ser una una categoria
    @ManyToOne
    @JoinColumn(name = "fk_categoria", nullable = false)
    private Categoria unaCategoria;

    //Muchos servicios pueden pertenecer a muchos paquetes
    @ManyToMany(mappedBy = "listaServicios")
    private Set<Paquete> listaPaquetes;

}
