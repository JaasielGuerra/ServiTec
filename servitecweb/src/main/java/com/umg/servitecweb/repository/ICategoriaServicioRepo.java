package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.CategoriaServicio;

public interface ICategoriaServicioRepo extends JpaRepository<CategoriaServicio, Integer> {

	public List<CategoriaServicio> findByEstado(Integer status);
}
