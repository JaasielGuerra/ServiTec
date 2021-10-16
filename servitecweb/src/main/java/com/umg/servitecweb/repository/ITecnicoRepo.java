package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Tecnico;

public interface ITecnicoRepo extends JpaRepository<Tecnico, Integer> {
	public List<Tecnico> findByEstado(Integer estado);
}
