package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Caja;
import com.umg.servitecweb.model.Ubicacion;

public interface IUbicacionRepo extends JpaRepository<Ubicacion, Integer>{
	public List<Ubicacion> findByEstado(Integer e);
}
