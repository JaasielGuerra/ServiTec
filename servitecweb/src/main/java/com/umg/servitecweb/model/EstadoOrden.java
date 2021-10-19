package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estado_orden database table.
 * 
 */
@Entity
@Table(name="estado_orden")
@NamedQuery(name="EstadoOrden.findAll", query="SELECT e FROM EstadoOrden e")
public class EstadoOrden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_estado_orden")
	private int idEstadoOrden;

	private String descripcion;

	private int estado;

	//bi-directional many-to-one association to Orden
	@OneToMany(mappedBy="estadoOrden")
	private List<Orden> ordens;

	public EstadoOrden() {
	}

	public int getIdEstadoOrden() {
		return this.idEstadoOrden;
	}

	public void setIdEstadoOrden(int idEstadoOrden) {
		this.idEstadoOrden = idEstadoOrden;
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
		orden.setEstadoOrden(this);

		return orden;
	}

	public Orden removeOrden(Orden orden) {
		getOrdens().remove(orden);
		orden.setEstadoOrden(null);

		return orden;
	}

}