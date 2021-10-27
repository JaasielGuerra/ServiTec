package com.umg.servitecweb.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Cliente;
import com.umg.servitecweb.model.EstadoOrden;
import com.umg.servitecweb.model.Orden;
import com.umg.servitecweb.model.Tecnico;

public interface IOrdenRepo extends JpaRepository<Orden, Long>{
	public Long countByTecnicoAndEstadoOrdenIn(Tecnico t, Collection<EstadoOrden> estados);
	public List<Orden> findByClienteAndEstadoOrdenIn(Cliente c, Collection<EstadoOrden> estados);
	public Orden findByIdOrdenAndCliente(Long id, Cliente c);
}
