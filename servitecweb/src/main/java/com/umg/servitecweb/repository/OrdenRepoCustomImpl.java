package com.umg.servitecweb.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.umg.servitecweb.model.Cliente;
import com.umg.servitecweb.model.EstadoOrden;
import com.umg.servitecweb.model.InventarioRepuesto;
import com.umg.servitecweb.model.MotivoOrden;
import com.umg.servitecweb.model.Orden;
import com.umg.servitecweb.model.Prioridad;
import com.umg.servitecweb.model.Tecnico;

@Repository
public class OrdenRepoCustomImpl implements OrdenRepoCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Orden> readByFilters(Tecnico t, Prioridad p, MotivoOrden m, Cliente c, Date fechaIngreso,
			EstadoOrden e) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT o FROM Orden o WHERE o.estadoOrden = :e ");

		if (t != null)
			query.append(" AND o.tecnico = :t ");
		if (p != null)
			query.append(" AND o.prioridad = :p ");
		if (m != null)
			query.append(" AND o.motivoOrden = :m ");
		if (c != null)
			query.append(" AND o.cliente = :c ");
		if (fechaIngreso != null)
			query.append(" AND o.fechaIngreso = :fecha ");

		TypedQuery<Orden> q = em.createQuery(query.toString(), Orden.class);
		q.setParameter("e", e);

		if (t != null)
			q.setParameter("t", t);
		if (p != null)
			q.setParameter("p", p);
		if (m != null)
			q.setParameter("m", m);
		if (c != null)
			q.setParameter("c", c);
		if (fechaIngreso != null)
			q.setParameter("fecha", fechaIngreso);

		return q.getResultList();
	}

}
