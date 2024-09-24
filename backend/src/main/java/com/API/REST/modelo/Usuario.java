package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "usuario",
        uniqueConstraints = @UniqueConstraint(columnNames = "correo_usuario"))

@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private int id;

    @Basic
    @Column(name = "nombre_usuario", length = 100, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Basic
    @Column(name = "apellido_usuario", length = 100, nullable = false)
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Basic
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "El correo no es valido")
    @Column(name = "correo_usuario", length = 100, nullable = false, unique = true)
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @Basic
    @Column(name = "clave_usuario", length = 100, nullable = false)
    @NotBlank(message = "La clave es obligatoria")
    private String clave;

    @Basic
    @Pattern(regexp = "^[0-9]{10,15}$", message = "El telefono no es valido")
    @Column(name = "telefono_usuario", nullable = false)
    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_nacimiento_usuario", nullable = false)
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private Date fechaNacimiento;

    @Basic
    @Column(name = "active_usuario", nullable = false)
    private boolean activo;

    //ENUMS
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo_usuario", nullable = false)
    private Sexo sexo;

    //TRIGGERS
    @PrePersist
    @PreUpdate
    public void convertirAMayusculas() {
        this.nombre = this.nombre.toUpperCase();
        this.apellido = this.apellido.toUpperCase();
    }


    //RELACIONES
    //Un cliente tiene muchos turnos
    @OneToMany(mappedBy = "cliente")
    private Set<Turno> turnosComoCliente;

    //Un profesional tiene muchos turnos
    @OneToMany(mappedBy = "profesional")
    private Set<Turno> turnosComoProfesional;

}
