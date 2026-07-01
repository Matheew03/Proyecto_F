package com.itsqmet.proyecto_f.Controller;

import com.itsqmet.proyecto_f.model.Perfil;
import com.itsqmet.proyecto_f.Service.PerfilService;
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
    public Perfil guardarPerfil(@RequestBody Perfil perfil) {
        return perfilService.guardarPerfil(perfil);
    }

    @PutMapping("/{id}")
    public Perfil actualizarPerfil(@PathVariable Long id, @RequestBody Perfil perfil) {
        perfil.setId(id);
        return perfilService.guardarPerfil(perfil);
    }

    @DeleteMapping("/{id}")
    public void eliminarPerfil(@PathVariable Long id) {
        perfilService.eliminarPerfil(id);
    }
}
