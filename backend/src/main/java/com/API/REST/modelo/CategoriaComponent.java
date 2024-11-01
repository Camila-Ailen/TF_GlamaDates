package com.API.REST.modelo;

import java.util.Set;

public interface CategoriaComponent {
    String getNombre();
    Set<Categoria> getSubcategorias();
    void agregarSubcategoria(Categoria subcategoria);
    void eliminarSubcategoria(Categoria subcategoria);
    void mostrarCategorias(int nivel);
}
