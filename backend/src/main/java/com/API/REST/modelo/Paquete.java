package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "paquete")
@Getter
@Setter
@NoArgsConstructor
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_paquete", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_paquete", length = 100, nullable = false)
    private String nombre;

    @Basic
    @Column(name = "descripcion_paquete", length = 100, nullable = false)
    private String descripcion;

    @Basic
    @Column(name = "precio_paquete", nullable = false)
    private double precio;

    @Basic
    @Column(name = "duracion_paquete", nullable = false)
    private int duracion;

    //RELACIONES
    //Un paquete tiene muchos turnos
    @OneToMany(mappedBy = "unPaquete", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Turno> unTurno;

    //Muchos paquetes pueden tener muchos servicios
    @ManyToMany
    @JoinTable(
            name = "paquete_servicio",
            joinColumns = @JoinColumn(name = "fk_paquete"),
            inverseJoinColumns = @JoinColumn(name = "fk_servicio")
    )
    private Set<Servicio> listaServicios;

}
