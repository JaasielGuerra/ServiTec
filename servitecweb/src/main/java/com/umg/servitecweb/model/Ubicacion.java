package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ubicacion database table.
 * 
 */
@Entity
@Table(name="ubicacion")
@NamedQuery(name="Ubicacion.findAll", query="SELECT u FROM Ubicacion u")
public class Ubicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_ubicacion")
	private int idUbicacion;

	private String descripcion;

	private int estado;

	//bi-directional many-to-one association to InventarioRepuesto
	@OneToMany(mappedBy="ubicacion")
	private List<InventarioRepuesto> inventarioRepuestos;

	public Ubicacion() {
	}

	public int getIdUbicacion() {
		return this.idUbicacion;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
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

	public List<InventarioRepuesto> getInventarioRepuestos() {
		return this.inventarioRepuestos;
	}

	public void setInventarioRepuestos(List<InventarioRepuesto> inventarioRepuestos) {
		this.inventarioRepuestos = inventarioRepuestos;
	}

	public InventarioRepuesto addInventarioRepuesto(InventarioRepuesto inventarioRepuesto) {
		getInventarioRepuestos().add(inventarioRepuesto);
		inventarioRepuesto.setUbicacion(this);

		return inventarioRepuesto;
	}

	public InventarioRepuesto removeInventarioRepuesto(InventarioRepuesto inventarioRepuesto) {
		getInventarioRepuestos().remove(inventarioRepuesto);
		inventarioRepuesto.setUbicacion(null);

		return inventarioRepuesto;
	}

}