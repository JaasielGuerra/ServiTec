package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
	
import com.umg.servitecweb.model.MotivoOrden;

public interface IMotivoOrdenRepo extends JpaRepository<MotivoOrden, Integer> {
	
	public List<MotivoOrden> findByEstado(Integer estado);

}
