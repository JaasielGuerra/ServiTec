package com.umg.servitecweb.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.EstadoOrden;

public interface IEstadoOrdenRepo extends JpaRepository<EstadoOrden, Integer>, OrdenRepoCustom{
	public List<EstadoOrden> findByIdEstadoOrdenIn(Collection<Integer> ids);
}
