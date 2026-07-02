package com.itsqmet.proyecto_f.Controller;

import com.itsqmet.proyecto_f.model.Cliente;
import com.itsqmet.proyecto_f.model.Empleado;
import com.itsqmet.proyecto_f.model.Proyecto;
import com.itsqmet.proyecto_f.Service.ProyectoService;
import com.itsqmet.proyecto_f.repository.ClienteRepository;
import com.itsqmet.proyecto_f.repository.EmpleadoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;

    public ProyectoController(ProyectoService proyectoService,
                              ClienteRepository clienteRepository,
                              EmpleadoRepository empleadoRepository) {
        this.proyectoService = proyectoService;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping
    public ResponseEntity<?> listarProyectos() {
        return ResponseEntity.ok(proyectoService.listarProyectos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> buscarPorId(@PathVariable Long id) {
        Optional<Proyecto> proyecto = proyectoService.buscarPorId(id);
        return proyecto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Proyecto> guardarProyecto(@Valid @RequestBody Proyecto proyecto) {
        Cliente cliente = clienteRepository.findById(proyecto.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        proyecto.setCliente(cliente);
        Proyecto nuevo = proyectoService.guardarProyecto(proyecto);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Long id,
                                                       @Valid @RequestBody Proyecto proyecto) {
        proyecto.setId(id);
        Cliente cliente = clienteRepository.findById(proyecto.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        proyecto.setCliente(cliente);
        Proyecto actualizado = proyectoService.guardarProyecto(proyecto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
    }

    // 🔗 Asignar empleado a proyecto
    @PutMapping("/{proyectoId}/empleados/{empleadoId}")
    public ResponseEntity<Proyecto> asignarEmpleado(@PathVariable Long proyectoId,
                                                    @PathVariable Long empleadoId) {
        Proyecto proyecto = proyectoService.buscarPorId(proyectoId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        proyecto.getEmpleados().add(empleado);
        Proyecto actualizado = proyectoService.guardarProyecto(proyecto);
        return ResponseEntity.ok(actualizado);
    }
}
