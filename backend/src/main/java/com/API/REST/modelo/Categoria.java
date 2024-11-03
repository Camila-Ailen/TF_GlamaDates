package com.API.REST.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
public class Categoria implements CategoriaComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_categoria", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nombre_categoria", length = 100, nullable = false)
    private String nombre;

    @Basic
    @Column(name = "observaciones_categoria", length = 100, nullable = true)
    private String observaciones;

    @Basic
    @Column(name = "activo_categoria", nullable = false)
    private boolean activo;


    //TRIGGERS
    @PrePersist
    @PreUpdate
    public void convertirAMayusculas() {
        this.nombre = this.nombre.toUpperCase();
        this.observaciones = this.observaciones.toUpperCase();
    }

    //RELACIONES
    //una categoria tiene muchos productos
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "unaCategoria")
    private Set<Producto> listaProductos;

    //una categoria tiene muchos servicios
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "unaCategoria")
    private Set<Servicio> listaServicios;

    //una categoria tiene muchas categorias
    @ManyToOne
    @JoinColumn(name = "fk_categoria_padre", nullable = true)
    private Categoria categoriaPadre;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "categoriaPadre")
    private Set<Categoria> subcategorias = new HashSet<>();



    //muchas categorias tiene muchas estaciones
    @ManyToMany
    @JoinTable(
            name = "categoria_estacion",
            joinColumns = @JoinColumn(name = "fk_categoria"),
            inverseJoinColumns = @JoinColumn(name = "fk_estacion")
    )
    private Set<Estacion> listaEstaciones;


    // Implementación de los métodos de CategoriaComponent

    @Override
    public Set<Categoria> getSubcategorias() {
        return subcategorias;
    }

    @Override
    public void agregarSubcategoria(Categoria subcategoria) {
        subcategorias.add(subcategoria);
        subcategoria.setCategoriaPadre(this);
    }

    @Override
    public void eliminarSubcategoria(Categoria subcategoria) {
        subcategorias.remove(subcategoria);
        subcategoria.setCategoriaPadre(null);
    }

    @Override
    public void mostrarCategorias(int nivel) {
        System.out.println("  ".repeat(nivel) + "- " + nombre);
        for (CategoriaComponent subcategoria : subcategorias) {
            subcategoria.mostrarCategorias(nivel + 1);
        }
    }

    public boolean esHoja() {
        return subcategorias == null || subcategorias.isEmpty();
    }
}
