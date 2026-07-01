package com.itsqmet.proyecto_f.repository;

import com.itsqmet.proyecto_f.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
