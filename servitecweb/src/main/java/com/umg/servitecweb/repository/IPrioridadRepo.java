package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Prioridad;

public interface IPrioridadRepo extends JpaRepository<Prioridad, Integer> {
	public List<Prioridad> findByEstado(Integer estado);
}
