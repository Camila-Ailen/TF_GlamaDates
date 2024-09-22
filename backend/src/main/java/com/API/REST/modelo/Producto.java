package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_producto", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_producto", length = 100, nullable = false)
    private String nombre;

    @Basic
    @Column(name = "precio_producto", nullable = false)
    private double precio;

    //RELACIONES
    //Un producto tiene muchos inventarios
    @OneToMany(mappedBy = "unProducto", orphanRemoval = true, cascade = CascadeType.ALL)
    private Inventario unInventario;

    //Muchos productos pertenecen a una categoria
    @ManyToOne
    @JoinColumn(name = "fk_categoria", nullable = false)
    private Categoria unaCategoria;

    //Muchos pruductos tienen una marca
    @ManyToOne
    @JoinColumn(name = "fk_marca", nullable = false)
    private Marca unaMarca;

    //Muchos productos tienen un tipo
    @ManyToOne
    @JoinColumn(name = "fk_tipo", nullable = false)
    private Tipo unTipo;

    //Un producto tiene muchas imagenes
    @OneToMany
    @JoinColumn(name = "entidad_id", referencedColumnName = "id_producto")
    private Set<Imagen> imagenes;
}
