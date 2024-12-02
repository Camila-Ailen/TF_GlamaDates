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
    private Long id;

    @Basic
    @Column(name = "fecha_turno", nullable = false)
    private LocalDate fecha;


    //ENUMS
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_turno", nullable = false)
    private TurnoEstado estado;

    //RELACIONES
    //Un turno puede tener, o no, un resultado
    @OneToOne(mappedBy = "unTurno", orphanRemoval = true, cascade = CascadeType.ALL)
    private Resultado unResultado;

    //Muchos turnos pueden pertenecer a un paquete
    @ManyToOne
    @JoinColumn(name = "fk_paquete", nullable = false)
    private Paquete unPaquete;

    //Muchos turnos pueden pertenecer a un cliente
    @ManyToOne
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Usuario cliente;

    //Muchos turnos pueden pertenecer a un profesional
    @ManyToOne
    @JoinColumn(name = "fk_profesional", nullable = false)
    private Usuario profesional;

    //Muchos turnos pueden pertenecer a una estacion
    @ManyToOne
    @JoinColumn(name = "estacion_id", nullable = false)
    private Estacion unaEstacion;

    //Muchos turnos pueden pertenecer a un horario
    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

}
