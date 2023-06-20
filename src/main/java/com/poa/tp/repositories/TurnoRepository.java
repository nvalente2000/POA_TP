package com.poa.tp.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poa.tp.entities.Turno;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
	
	Optional <Turno> findByFechaHoraTurno (LocalDateTime fechaHoraTurno);
	void deleteByFechaHoraTurno (LocalDateTime fechaHoraTurno);

}
