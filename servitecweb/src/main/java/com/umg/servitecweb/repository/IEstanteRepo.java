package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Caja;
import com.umg.servitecweb.model.Estante;

public interface IEstanteRepo extends JpaRepository<Estante, Integer>{
	public List<Estante> findByEstado(Integer e);
}
