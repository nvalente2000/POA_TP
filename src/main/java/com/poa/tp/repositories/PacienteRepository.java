package com.poa.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poa.tp.entities.Paciente;


public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
