package com.API.REST.controlador;

import com.API.REST.modelo.Categoria;
import com.API.REST.servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size,
                                   @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                   @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
                                   @RequestParam(name = "activo", required = false) Boolean activo,
                                   Model model) {

        // Busca y guarda en variable categorias la lista de todas las categorias
        List<Categoria> categorias = categoriaService.findAll();

        // Ordena la lista de categorias
        System.out.println("desde controlador voy a ir a ordenar");
        categorias = categoriaService.sortCategorias(categorias, sortField, sortDir);

        // Obtiene la página de categorias filtradas y ordenadas
        Page<Categoria> categoriaPage = categoriaService.getPaginatedCategorias(categorias, PageRequest.of(page, size));

        model.addAttribute("categoriaPage", categoriaPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoriaPage.getTotalPages());

        model.addAttribute("categorias", categoriaPage.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("activo", activo);
        model.addAttribute("totalCategorias", categorias.size());

        model.addAttribute("contenido", "admin/categorias/lista");
        return "admin/principal";
    }

    @GetMapping("/crear")
    public String nuevaCategoria(Model model) {
        model.addAttribute("modal", true);

        model.addAttribute("contenido", "admin/categorias/form");
        return "admin/principal";
    }

    @PostMapping
    public String postCategoria(@Valid @ModelAttribute Categoria categoria) {
        categoria.setActivo(true);
        categoriaService.save(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/{id}/editar")
    public String editCategoriaForm(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid categoria Id:" + id));
        model.addAttribute("contenido", "admin/categorias/form");
        return "admin/principal";
    }

    @PutMapping("/{id}")
    public String updateCategoria(@PathVariable("id") Long id, @ModelAttribute Categoria categoria, BindingResult resultado, RedirectAttributes redirectAttributes, Model model) {
        if (resultado.hasErrors()) {
            categoria.setId(id);
            model.addAttribute("contenido", "admin/categorias/modificar");
            return "admin/principal";
        }
        categoriaService.updateCategoria(id, categoria);
        redirectAttributes.addFlashAttribute("mensaje", "Categoria actualizada correctamente");
        return "redirect:/categorias";
    }

    @DeleteMapping("/{id}")
    public String eliminarCategoria(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.softDeleteCategoria(id);
            redirectAttributes.addFlashAttribute("mensaje", "Categoria eliminada exitosamente.");
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            // Agregar mensaje de error
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la categoria.");
            return "redirect:/admin/categorias";
        }
    }

    @PutMapping("/{id}/activar")
    public String activarCategoria(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.activarCategoria(id);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario activado exitosamente.");
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            // Agregar mensaje de error
            redirectAttributes.addFlashAttribute("error", "Error al activar el usuario.");
            return "redirect:/admin/categorias";
        }
    }


}
