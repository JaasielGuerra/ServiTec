package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Servicio;

public interface IServicioRepo extends JpaRepository<Servicio, Integer> {

	public List<Servicio> findByEstado(Integer status);
}
	