package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_usuario", length = 100, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Basic
    @Column(name = "apellido_usuario", length = 100, nullable = false)
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Basic
    @Column(name = "correo_usuario", length = 100, nullable = false, unique = true)
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @Basic
    @Column(name = "clave_usuario", length = 100, nullable = false)
    @NotBlank(message = "La clave es obligatoria")
    private String clave;

    @Basic
    @Column(name = "telefono_usuario", length = 20, nullable = false)
    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    @Basic
    @Column(name = "fecha_nacimiento_usuario", nullable = false)
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @Basic
    @Column(name = "activo_usuario", nullable = false)
    private boolean activo;

    //ENUMS
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo_usuario", nullable = false)
    private Sexo sexo;
}
