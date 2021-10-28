package com.umg.servitecweb.repository;

import java.util.List;

import com.umg.servitecweb.model.Caja;
import com.umg.servitecweb.model.Estante;
import com.umg.servitecweb.model.InventarioRepuesto;
import com.umg.servitecweb.model.Ubicacion;

public interface IventarioRepoCustom {
	public List<InventarioRepuesto> findByFilter(Estante es, Ubicacion u, Caja c);
}
