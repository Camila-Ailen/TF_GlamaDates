package com.API.REST.controlador;

import com.API.REST.modelo.Rol;
import com.API.REST.modelo.Sexo;
import com.API.REST.modelo.Usuario;
import com.API.REST.servicios.RolService;
import com.API.REST.servicios.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final RolService rolService;
/*
    @GetMapping("/todoUsuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuariosActivos() {
        return usuarioService.findAllUsuariosActivos();
    }

 */

// Este ya es como el de Biale
@GetMapping()
public String index(@RequestParam(name = "sortField", defaultValue = "id") String sortField,
                    @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
                    @RequestParam(name = "activo", required = false) Boolean activo,
                    @RequestParam(name = "rol", required = false) String rol,
                    @RequestParam(name = "sexo", required = false) Sexo sexo,
                    Model modelo) {
    // Filtrar los usuarios
    List<Usuario> usuarios = filterUsuarios(activo, rol, sexo);
    // Ordenar la lista filtrada
    usuarios = usuarioService.sortUsuarios(usuarios, sortField, sortDir);

    var roles = rolService.findAllRoles();
    var sexos = Sexo.values();

    modelo.addAttribute("usuarios", usuarios);
    modelo.addAttribute("roles", roles);
    modelo.addAttribute("sexos", sexos);
    modelo.addAttribute("sortField", sortField);
    modelo.addAttribute("sortDir", sortDir);
    modelo.addAttribute("reverseSortDir", sortDir.equals("desc") ? "asc" : "desc");
    modelo.addAttribute("activo", activo);
    modelo.addAttribute("rol", rol);
    modelo.addAttribute("sexo", sexo);
    modelo.addAttribute("totalUsuarios", usuarios.size());
    return "admin/usuarios";
}

    private List<Usuario> filterUsuarios(Boolean activo, String rol, Sexo sexo) {
        return usuarioService.findAllUsuariosFiltered(activo, rol, sexo);
    }

    @GetMapping("/usuarios/crear")
    public String nuevoUsuario(Model modelo) {
        var usuario = new Usuario();
        modelo.addAttribute("modal", true);
        return "/usuarios";
    }



/*


    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") Integer usuarioId) {
        return usuarioService.findUsuarioById(usuarioId);
    }

 */

    @PostMapping()
    public String postUsuario(@Valid @ModelAttribute Usuario usuario) {
        if (usuario.getClave() == null || usuario.getClave().isEmpty()) {
            usuario.setClave("12345678");
        }
        //pasar el estado activo
        usuario.setActivo(true);
        usuarioService.saveUsuario(usuario);
        return "admin/usuarios";
    }

    @GetMapping("/{id}/editar")
    public String editarUsuario(@PathVariable("id") Integer id, Model modelo) {
        Usuario usuario = usuarioService.findUsuarioById(id);
        List<Rol> roles = rolService.findAllRoles();
        Sexo[] sexos = Sexo.values();
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("roles", roles);
        modelo.addAttribute("sexos", sexos);
        return "admin/usuarioModificar";
    }

    @PutMapping("/{id}")
    public String actualizarUsuario(@PathVariable("id") Integer id, @ModelAttribute Usuario usuario, BindingResult resultado, RedirectAttributes redirectAttributes) {
        System.out.println("Entre al controlador de usuario");
        if (resultado.hasErrors()) {
            System.out.println("Hay errores");
            return "admin/usuarioModificar";
        }
        System.out.println("voy a llamar al servicio");
        usuarioService.updateUsuario(id, usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado exitosamente");
        return "redirect:/usuarios";
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
        usuarioService.softDeleteUsuario(id);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado exitosamente.");
            return "redirect:/usuarios";  // Redirigir a la lista de usuarios (o la página que quieras)
        } catch (Exception e) {
            // Agregar mensaje de error
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el usuario.");
            return "redirect:/usuarios";  // Redirigir a la lista de usuarios (o la página que quieras)
        }
    }

    @PutMapping("/{id}/activar")
    public String activarUsuario(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.activarUsuario(id);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario activado exitosamente.");
            return "redirect:/usuarios";  // Redirigir a la lista de usuarios
        } catch (Exception e) {
            // Agregar mensaje de error
            redirectAttributes.addFlashAttribute("error", "Error al activar el usuario.");
            return "redirect:/usuarios";  // Redirigir a la lista de usuarios
        }
    }

/*
@PostMapping("/usuarios")
    public Usuario postUsuario(@Valid @ModelAttribute Usuario usuario) {
        if (usuario.getClave() == null || usuario.getClave().isEmpty()) {
            usuario.setClave("12345678");
        }
        //pasar el estado activo
        usuario.setActivo(true);
        return usuarioService.saveUsuario(usuario);
    }


    @PutMapping("/usuarios/{id}")
    public Usuario putUsuario(@PathVariable(value = "id") Integer usuarioId,
                              @Valid @RequestBody Usuario usuarioDetails) {
        return usuarioService.updateUsuario(usuarioId, usuarioDetails);
    }


    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Integer usuarioId) {
        return usuarioService.softDeleteUsuario(usuarioId);
    }

*/

}
