package com.API.REST.modelo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "imagen")
@Getter
@Setter
@NoArgsConstructor
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_imagen", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_imagen", length = 100, nullable = true)
    private String nombre;

    @Basic
    @Column(name = "url_imagen", length = 255, nullable = false)
    private String url;

    @Basic
    @Column(name = "tipo_entidad", nullable = false)
    private String tipoEntidad;

    @Basic
    @Column(name = "entidad_id", nullable = false)
    private long entidadId;
/*
    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "entidad_id", insertable = false, updatable = false)
    private EntidadConImagen entidad;

 */
}
