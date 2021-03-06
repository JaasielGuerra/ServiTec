package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Caja;
import com.umg.servitecweb.model.Estante;
import com.umg.servitecweb.model.InventarioRepuesto;
import com.umg.servitecweb.model.Ubicacion;

public interface IInventarioRepo extends JpaRepository<InventarioRepuesto, Long>, IventarioRepoCustom {
	public List<InventarioRepuesto> findByEstado(Integer e);
	
}
