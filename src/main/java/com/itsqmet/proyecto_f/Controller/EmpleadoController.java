package com.itsqmet.proyecto_f.Controller;

import com.itsqmet.proyecto_f.model.Empleado;
import com.itsqmet.proyecto_f.Service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/{id}")
    public Optional<Empleado> buscarPorId(@PathVariable Long id) {
        return empleadoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Empleado> guardarEmpleado(@Valid @RequestBody Empleado empleado) {
        Empleado nuevo = empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @Valid @RequestBody Empleado empleado) {
        empleado.setId(id);
        Empleado actualizado = empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
    }
}
