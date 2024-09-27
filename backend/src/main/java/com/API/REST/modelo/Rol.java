package com.API.REST.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    //Muchos roles tienen muchos usuarios
    @ManyToMany(mappedBy = "listaRoles")
    @JsonBackReference
    private Set<Usuario> listaUsuarios;

    //Muchos roles tienen muchos permisos
    @ManyToMany
    @JoinTable(
            name = "rol_permiso",
            joinColumns = @JoinColumn(name = "id_rol"),
            inverseJoinColumns = @JoinColumn(name = "id_permiso"))
    @JsonManagedReference
    private Set<Permiso> listaPermisos;

}
