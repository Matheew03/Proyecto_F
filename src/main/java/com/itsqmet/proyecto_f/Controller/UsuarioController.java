package com.itsqmet.proyecto_f.Controller;

import com.itsqmet.proyecto_f.model.Usuario;
import com.itsqmet.proyecto_f.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (usuarioService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Usuario eliminado"));
        }
        return ResponseEntity.status(404).body(Map.of("error", "Usuario no encontrado"));
    }
}
