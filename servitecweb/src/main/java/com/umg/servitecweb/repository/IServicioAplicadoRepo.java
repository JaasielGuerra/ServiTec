package com.umg.servitecweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.servitecweb.model.Orden;
import com.umg.servitecweb.model.ServicioAplicado;

public interface IServicioAplicadoRepo extends JpaRepository<ServicioAplicado, Long> {

	public List<ServicioAplicado> findByOrden(Orden o);

}
