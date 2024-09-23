package com.API.REST.modelo;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public abstract class EntidadConImagen {
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "entidad")
    private Set<Imagen> listaImagenes;

    // Getters y Setters
    public Set<Imagen> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(Set<Imagen> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }
}