package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "marca")
@Getter
@Setter
@NoArgsConstructor
public class Marca{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_marca", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_marca", length = 100, nullable = false)
    private String nombre;

    //RELACIONES
    //una marca tiene muchos productos
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "unaMarca")
    private Set<Producto> listaProductos;

}
