package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Caja;
import com.umg.servitecweb.model.Estante;
import com.umg.servitecweb.model.InventarioRepuesto;
import com.umg.servitecweb.model.Ubicacion;

public interface IInventarioRepo extends JpaRepository<InventarioRepuesto, Long>{
	public List<InventarioRepuesto> findByEstado(Integer e);
	public List<InventarioRepuesto> findByEstadoAndEstanteAndUbicacionAndCaja(Integer e, Estante es, Ubicacion u, Caja c);
	public List<InventarioRepuesto> findByEstanteAndUbicacionAndCaja(Estante es, Ubicacion u, Caja c);
}
