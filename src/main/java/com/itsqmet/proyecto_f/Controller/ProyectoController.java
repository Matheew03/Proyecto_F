package com.itsqmet.proyecto_f.Controller;

import com.itsqmet.proyecto_f.model.Proyecto;
import com.itsqmet.proyecto_f.Service.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public List<Proyecto> listarProyectos() {
        return proyectoService.listarProyectos();
    }

    @GetMapping("/{id}")
    public Optional<Proyecto> buscarPorId(@PathVariable Long id) {
        return proyectoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Proyecto> guardarProyecto(@Valid @RequestBody Proyecto proyecto) {
        Proyecto nuevo = proyectoService.guardarProyecto(proyecto);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Long id, @Valid @RequestBody Proyecto proyecto) {
        proyecto.setId(id);
        Proyecto actualizado = proyectoService.guardarProyecto(proyecto);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public void eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
    }
}
