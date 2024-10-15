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
    public String index (Model modelo){
        var usuarios = usuarioService.findAllUsuarios();
        var roles = rolService.findAllRoles();
        var sexos = Sexo.values();
        modelo.addAttribute("usuarios", usuarios);
        modelo.addAttribute("roles", roles);
        modelo.addAttribute("sexos", sexos);
        return "admin/usuarios";
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
    public String actualizarUsuario(@PathVariable("id") Integer id, @ModelAttribute Usuario usuario, BindingResult resultado) {
        System.out.println("Entre al controlador de usuario");
        if (resultado.hasErrors()) {
            System.out.println("Hay errores");
            return "admin/usuarioModificar";
        }
        System.out.println("voy a llamar al servicio");
        usuarioService.updateUsuario(id, usuario);
        return "redirect:/usuarios";
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
