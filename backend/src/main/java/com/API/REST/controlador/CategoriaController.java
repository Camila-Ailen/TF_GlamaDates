package com.API.REST.controlador;

import com.API.REST.modelo.Categoria;
import com.API.REST.servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listCategorias(Model model) {
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        return "admin/categorias/list";
    }

    @GetMapping("/crear")
    public String createCategoriaForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/categorias/form";
    }

    @PostMapping
    public String saveCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.save(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/{id}/editar")
    public String editCategoriaForm(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid categoria Id:" + id));
        model.addAttribute("categoria", categoria);
        return "admin/categorias/form";
    }

    @PostMapping("/{id}")
    public String updateCategoria(@PathVariable Long id, @ModelAttribute Categoria categoria) {
        categoria.setId(id);
        categoriaService.save(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/{id}/eliminar")
    public String deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return "redirect:/categorias";
    }

}
