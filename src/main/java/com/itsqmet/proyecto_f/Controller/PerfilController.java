package com.itsqmet.proyecto_f.Controller;

import com.itsqmet.proyecto_f.model.Perfil;
import com.itsqmet.proyecto_f.Service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public List<Perfil> listarPerfiles() {
        return perfilService.listarPerfiles();
    }

    @GetMapping("/{id}")
    public Optional<Perfil> buscarPorId(@PathVariable Long id) {
        return perfilService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Perfil> guardarPerfil(@Valid @RequestBody Perfil perfil) {
        Perfil nuevo = perfilService.guardarPerfil(perfil);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> actualizarPerfil(@PathVariable Long id, @Valid @RequestBody Perfil perfil) {
        perfil.setId(id);
        Perfil actualizado = perfilService.guardarPerfil(perfil);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public void eliminarPerfil(@PathVariable Long id) {
        perfilService.eliminarPerfil(id);
    }
}
