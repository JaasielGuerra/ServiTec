package com.umg.servitecweb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.umg.servitecweb.model.Caja;
import com.umg.servitecweb.model.Estante;
import com.umg.servitecweb.model.InventarioRepuesto;
import com.umg.servitecweb.model.Ubicacion;

@Repository
public class IventarioRepoCustomImpl implements IventarioRepoCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<InventarioRepuesto> findByFilter(Estante es, Ubicacion u,
			Caja c) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT i FROM InventarioRepuesto i WHERE i.estado IN(1,0) ");

		if (es != null)
			query.append(" AND i.estante = :es ");

		if (u != null)
			query.append(" AND i.ubicacion = :ub ");

		if (c != null)
			query.append(" AND i.caja = :ca ");

		TypedQuery<InventarioRepuesto> q = em.createQuery(query.toString(), InventarioRepuesto.class);

		if (es != null)
			q.setParameter("es", es);

		if (u != null)
			q.setParameter("ub", u);

		if (c != null)
			q.setParameter("ca", c);

		return q.getResultList();
	}

}
