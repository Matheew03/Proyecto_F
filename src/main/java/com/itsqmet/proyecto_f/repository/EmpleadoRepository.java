package com.itsqmet.proyecto_f.repository;

import com.itsqmet.proyecto_f.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
