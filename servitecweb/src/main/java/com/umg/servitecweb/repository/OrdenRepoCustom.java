package com.umg.servitecweb.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.umg.servitecweb.model.Cliente;
import com.umg.servitecweb.model.EstadoOrden;
import com.umg.servitecweb.model.MotivoOrden;
import com.umg.servitecweb.model.Orden;
import com.umg.servitecweb.model.Prioridad;
import com.umg.servitecweb.model.Tecnico;

public interface OrdenRepoCustom {
	public List<Orden> readByFilters(Tecnico t, Prioridad p, MotivoOrden m, Cliente c, Date fechaIngreso,
			EstadoOrden e);

	public List<Orden> readByFiltersAndEstados(Tecnico t, Prioridad p, MotivoOrden m, Cliente c, Date fechaIngreso,
			Collection<EstadoOrden> e);
	
	public List<Orden> readByIdOrdenAndClienteAndEstado(Long id, Cliente c, EstadoOrden e);
}
