package com.API.REST.servicios;

import com.API.REST.modelo.Categoria;
import com.API.REST.repositorio.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public List<Categoria> findCategoriasPadre(){
        return categoriaRepository.findByCategoriaPadreIsNull();
    }

    public List<Categoria> sortCategorias(List<Categoria> categorias, String sortField, String sortDir) {
        categorias.sort((c1, c2) -> {
            if (sortDir.equals("asc")) {
                switch (sortField) {
                    case "id":
                        return Long.compare(c1.getId(), c2.getId());
                    case "nombre":
                        return c1.getNombre().compareTo(c2.getNombre());
                    case "observaciones":
                        return c1.getObservaciones().compareTo(c2.getObservaciones());
                    case "activo":
                        return Boolean.compare(c1.isActivo(), c2.isActivo());
                    case "categoriaPadre.nombre":
                        if (c1.getCategoriaPadre() == null && c2.getCategoriaPadre() == null) {
                            return 0;
                        } else if (c1.getCategoriaPadre() == null) {
                            return -1;
                        } else if (c2.getCategoriaPadre() == null) {
                            return 1;
                        } else {
                            return c1.getCategoriaPadre().getNombre().compareTo(c2.getCategoriaPadre().getNombre());
                        }
                    default:
                        return 0;
                }
            } else {
                switch (sortField) {
                    case "id":
                        return Long.compare(c2.getId(), c1.getId());
                    case "nombre":
                        return c2.getNombre().compareTo(c1.getNombre());
                    case "observaciones":
                        return c2.getObservaciones().compareTo(c1.getObservaciones());
                    case "activo":
                        return Boolean.compare(c2.isActivo(), c1.isActivo());
                    case "categoriaPadre.nombre":
                        if (c1.getCategoriaPadre() == null && c2.getCategoriaPadre() == null) {
                            return 0;
                        } else if (c1.getCategoriaPadre() == null) {
                            return -1;
                        } else if (c2.getCategoriaPadre() == null) {
                            return 1;
                        } else {
                            return c2.getCategoriaPadre().getNombre().compareTo(c1.getCategoriaPadre().getNombre());
                        }
                    default:
                        return 0;
                }
            }
        });
        return categorias;
    }



    public Page<Categoria> getPaginatedCategorias(List<Categoria> categorias, PageRequest pageRequest) {
        int pageSize = pageRequest.getPageSize();
        int currentPage = pageRequest.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Categoria> list;

        if (categorias.size() < startItem) {
            list = List.of();
        } else {
            int toIndex = Math.min(startItem + pageSize, categorias.size());
            list = categorias.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), categorias.size());
    }

    public void updateCategoria(long id, Categoria categoria) {
        Categoria categoriaActual = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid categoria Id:" + id));
        categoriaActual.setNombre(categoria.getNombre());
        categoriaActual.setObservaciones(categoria.getObservaciones());

        if (categoria.isActivo() != categoriaActual.isActivo()) {
            categoriaActual.setActivo(categoria.isActivo());
        }

        // Verificar y actualizar la categoría padre
        if (categoria.getCategoriaPadre() != null) {
            Categoria categoriaPadre = categoriaRepository.findById(categoria.getCategoriaPadre().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid categoria padre Id:" + categoria.getCategoriaPadre().getId()));
            categoriaActual.setCategoriaPadre(categoriaPadre);
        } else {
            categoriaActual.setCategoriaPadre(null);
        }
        categoriaRepository.save(categoriaActual);
    }

    public void softDeleteCategoria(long id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid categoria Id:" + id));
        categoria.setActivo(false);
        categoriaRepository.save(categoria);
    }

    public void activarCategoria(long id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid categoria Id:" + id));
        categoria.setActivo(true);
        categoriaRepository.save(categoria);
    }

    public Optional<Categoria> findById(long id){
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void deleteById(long id){
        categoriaRepository.deleteById(id);
    }
}
