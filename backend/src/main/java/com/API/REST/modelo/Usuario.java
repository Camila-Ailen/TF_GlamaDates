package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Integer id;
    @Column(name = "nombre_usuario", length = 100, nullable = false)
    private String nombre;
    @Column(name = "apellido_usuario", length = 100, nullable = false)
    private String apellido;
    @Column(name = "correo_usuario", length = 100, nullable = false, unique = true)
    private String correo;
    @Column(name = "clave_usuario", length = 100, nullable = false)
    private String clave;
    @Column(name = "telefono_usuario", length = 20, nullable = false)
    private String telefono;
    @Column(name = "fecha_nacimiento_usuario", nullable = false)
    private LocalDate fechaNacimiento;
    @Column(name = "activo", nullable = false)
    private boolean activo;
}
