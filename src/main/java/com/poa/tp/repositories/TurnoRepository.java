package com.poa.tp.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poa.tp.entities.Turno;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
	
	Optional <Turno> findByFechaHoraTurno (Date fechaHoraTurno);
	void deleteByFechaHoraTurno (Date fechaHoraTurno);

}
