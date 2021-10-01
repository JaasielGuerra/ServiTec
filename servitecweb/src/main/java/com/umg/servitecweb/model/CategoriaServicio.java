package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the categoria_servicio database table.
 * 
 */
@Entity
@Table(name="categoria_servicio")
@NamedQuery(name="CategoriaServicio.findAll", query="SELECT c FROM CategoriaServicio c")
public class CategoriaServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_categoria_servicio")
	private int idCategoriaServicio;

	private String descripcion;

	private int estado;

	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="categoriaServicio")
	private List<Servicio> servicios;


	public CategoriaServicio() {
		
	}
	
	public int getIdCategoriaServicio() {
		return this.idCategoriaServicio;
	}

	public void setIdCategoriaServicio(int idCategoriaServicio) {
		this.idCategoriaServicio = idCategoriaServicio;
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

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio addServicio(Servicio servicio) {
		getServicios().add(servicio);
		servicio.setCategoriaServicio(this);

		return servicio;
	}

	public Servicio removeServicio(Servicio servicio) {
		getServicios().remove(servicio);
		servicio.setCategoriaServicio(null);

		return servicio;
	}

}