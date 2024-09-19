package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "resultado")
@Getter
@Setter
@NoArgsConstructor
public class Resultado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_resultado", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "observacion_resultado", length = 100, nullable = false)
    private String observacion;

    @Basic
    @Column(name = "comentario_resultado", length = 100, nullable = false)
    private String comentario;
}
