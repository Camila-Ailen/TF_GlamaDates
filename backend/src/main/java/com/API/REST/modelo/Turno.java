package com.API.REST.modelo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "turno")
@Getter
@Setter
@NoArgsConstructor
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_turno", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "fecha_turno", nullable = false)
    private Date fecha;

    @Basic
    @Column(name = "hora_turno", nullable = false)
    private String hora;

    //ENUMS
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_turno", nullable = false)
    private TurnoEstado estado;
}
