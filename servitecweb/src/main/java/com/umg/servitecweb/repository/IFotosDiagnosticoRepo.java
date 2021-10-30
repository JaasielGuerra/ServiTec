package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.FotosDiagnostico;
import com.umg.servitecweb.model.Orden;

public interface IFotosDiagnosticoRepo extends JpaRepository<FotosDiagnostico, Long> {
	public List<FotosDiagnostico> findByOrden(Orden o);
}
