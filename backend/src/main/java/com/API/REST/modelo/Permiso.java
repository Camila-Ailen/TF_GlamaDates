package com.API.REST.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permiso")
@Getter
@Setter
@NoArgsConstructor
public class Permiso {
    @Id
    @GeneratedValue
    @Column(name = "id_permiso", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_permiso", length = 100, nullable = false)
    private String nombre;

    @Basic
    @Column(name = "observacion_permiso", length = 100, nullable = false)
    private String observacion;

    //RELACIONES
    //Muchos permisos tienen muchos roles
    @ManyToMany(mappedBy = "listaPermisos")
    @JsonBackReference
    private Set<Rol> listaRoles;
}
