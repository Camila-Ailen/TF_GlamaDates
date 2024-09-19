package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventario")
@Getter
@Setter
@NoArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_inventario", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "precio_inventario", nullable = false)
    private double precio;

    @Basic
    @Column(name = "fecha_inventario", nullable = false)
    private Date fecha;

    //ENUMS
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_inventario", nullable = false)
    private InventarioEstado estado;


}
