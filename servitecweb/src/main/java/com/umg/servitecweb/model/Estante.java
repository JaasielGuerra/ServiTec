package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estante database table.
 * 
 */
@Entity
@Table(name="estante")
@NamedQuery(name="Estante.findAll", query="SELECT e FROM Estante e")
public class Estante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_estante")
	private int idEstante;

	private String descripcion;

	private int estado;

	private int numero;

	//bi-directional many-to-one association to InventarioRepuesto
	@OneToMany(mappedBy="estante")
	private List<InventarioRepuesto> inventarioRepuestos;

	public Estante() {
	}

	public int getIdEstante() {
		return this.idEstante;
	}

	public void setIdEstante(int idEstante) {
		this.idEstante = idEstante;
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
		inventarioRepuesto.setEstante(this);

		return inventarioRepuesto;
	}

	public InventarioRepuesto removeInventarioRepuesto(InventarioRepuesto inventarioRepuesto) {
		getInventarioRepuestos().remove(inventarioRepuesto);
		inventarioRepuesto.setEstante(null);

		return inventarioRepuesto;
	}

}