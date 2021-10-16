package com.umg.servitecweb.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.EstadoOrden;
import com.umg.servitecweb.model.Orden;
import com.umg.servitecweb.model.Tecnico;

public interface IOrdenRepo extends JpaRepository<Orden, Long>{
	public Long countByTecnicoAndEstadoOrdenIn(Tecnico t, Collection<EstadoOrden> estados);
}
