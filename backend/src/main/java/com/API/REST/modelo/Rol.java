package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rol", nullable = false, unique = true)
    private int id;

    @Basic
    @Column(name = "nombre_rol", length = 100, nullable = false)
    private String nombre;

    @Basic
    @Column(name = "descripcion_rol", length = 100, nullable = false)
    private String descripcion;

    //RELACIONES
    //Un rol tiene muchos usuarios
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "unRol")
    private Set<Usuario> listaUsuarios;

}
