package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sexo")
@Getter
@Setter
@NoArgsConstructor
public class Sexo {
    @Id
    @Column(name = "id_sexo", nullable = false, unique = true)
    private Integer id;
    @Column(name = "valor_sexo", length = 50, nullable = false, unique = true)
    private String valor;
    @Column(name = "activo", nullable = false)
    private boolean activo;
}
