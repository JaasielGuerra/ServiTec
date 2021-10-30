package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Orden;
import com.umg.servitecweb.model.RepuestoEmpleado;

public interface IRepuestoEmpleadoRepo extends JpaRepository<RepuestoEmpleado, Long> {
	
	public List<RepuestoEmpleado> findByOrden(Orden o);

}
