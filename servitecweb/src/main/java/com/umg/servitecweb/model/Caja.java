package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the caja database table.
 * 
 */
@Entity
@Table(name="caja")
@NamedQuery(name="Caja.findAll", query="SELECT c FROM Caja c")
public class Caja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_caja")
	private int idCaja;

	private String descripcion;

	private int estado;

	private int numero;

	//bi-directional many-to-one association to InventarioRepuesto
	@OneToMany(mappedBy="caja")
	private List<InventarioRepuesto> inventarioRepuestos;

	public Caja() {
	}

	public int getIdCaja() {
		return this.idCaja;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
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

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<InventarioRepuesto> getInventarioRepuestos() {
		return this.inventarioRepuestos;
	}

	public void setInventarioRepuestos(List<InventarioRepuesto> inventarioRepuestos) {
		this.inventarioRepuestos = inventarioRepuestos;
	}

	public InventarioRepuesto addInventarioRepuesto(InventarioRepuesto inventarioRepuesto) {
		getInventarioRepuestos().add(inventarioRepuesto);
		inventarioRepuesto.setCaja(this);

		return inventarioRepuesto;
	}

	public InventarioRepuesto removeInventarioRepuesto(InventarioRepuesto inventarioRepuesto) {
		getInventarioRepuestos().remove(inventarioRepuesto);
		inventarioRepuesto.setCaja(null);

		return inventarioRepuesto;
	}

}