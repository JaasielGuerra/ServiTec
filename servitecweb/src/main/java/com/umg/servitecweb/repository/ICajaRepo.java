package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Caja;

public interface ICajaRepo extends JpaRepository<Caja, Integer>{
	public List<Caja> findByEstado(Integer e);
}
