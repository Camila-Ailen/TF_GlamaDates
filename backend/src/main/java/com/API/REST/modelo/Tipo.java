package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "tipo")
@Getter
@Setter
@NoArgsConstructor
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_tipo", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_tipo", length = 100, nullable = false)
    private String nombre;

    //RELACIONES
    //Un tipo tiene muchos productos
    @OneToMany(mappedBy = "unTipo", orphanRemoval = true, cascade = CascadeType.ALL)
    private Producto unProducto;

}
