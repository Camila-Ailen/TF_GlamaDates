package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_producto", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_producto", length = 100, nullable = false)
    private String nombre;

    @Basic
    @Column(name = "precio_producto", nullable = false)
    private double precio;
}
