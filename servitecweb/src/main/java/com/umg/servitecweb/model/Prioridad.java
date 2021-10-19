package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the prioridad database table.
 * 
 */
@Entity
@Table(name="prioridad")
@NamedQuery(name="Prioridad.findAll", query="SELECT p FROM Prioridad p")
public class Prioridad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prioridad")
	private int idPrioridad;

	private String descripcion;

	private int estado;

	//bi-directional many-to-one association to Orden
	@OneToMany(mappedBy="prioridad")
	private List<Orden> ordens;

	public Prioridad() {
	}

	public int getIdPrioridad() {
		return this.idPrioridad;
	}

	public void setIdPrioridad(int idPrioridad) {
		this.idPrioridad = idPrioridad;
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
		orden.setPrioridad(this);

		return orden;
	}

	public Orden removeOrden(Orden orden) {
		getOrdens().remove(orden);
		orden.setPrioridad(null);

		return orden;
	}

}