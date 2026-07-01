package com.itsqmet.proyecto_f.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    // Relación 1:N con Proyecto
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Proyecto> proyectos;
}
