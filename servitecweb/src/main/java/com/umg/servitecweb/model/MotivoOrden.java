package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the motivo_orden database table.
 * 
 */
@Entity
@Table(name="motivo_orden")
@NamedQuery(name="MotivoOrden.findAll", query="SELECT m FROM MotivoOrden m")
public class MotivoOrden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_motivo_orden")
	private int idMotivoOrden;

	private String descripcion;

	private int estado;

	//bi-directional many-to-one association to Orden
	@OneToMany(mappedBy="motivoOrden")
	private List<Orden> ordens;

	public MotivoOrden() {
	}

	public int getIdMotivoOrden() {
		return this.idMotivoOrden;
	}

	public void setIdMotivoOrden(int idMotivoOrden) {
		this.idMotivoOrden = idMotivoOrden;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<Orden> getOrdens() {
		return this.ordens;
	}

	public void setOrdens(List<Orden> ordens) {
		this.ordens = ordens;
	}

	public Orden addOrden(Orden orden) {
		getOrdens().add(orden);
		orden.setMotivoOrden(this);

		return orden;
	}

	public Orden removeOrden(Orden orden) {
		getOrdens().remove(orden);
		orden.setMotivoOrden(null);

		return orden;
	}

}