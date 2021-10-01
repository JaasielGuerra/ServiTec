package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the repuesto_empleado database table.
 * 
 */
@Entity
@Table(name="repuesto_empleado")
@NamedQuery(name="RepuestoEmpleado.findAll", query="SELECT r FROM RepuestoEmpleado r")
public class RepuestoEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_repuesto_empleado")
	private long idRepuestoEmpleado;

	private int cantidad;

	private double precio;

	//bi-directional many-to-one association to InventarioRepuesto
	@ManyToOne
	@JoinColumn(name="id_inventario_repuesto")
	private InventarioRepuesto inventarioRepuesto;

	//bi-directional many-to-one association to Orden
	@ManyToOne
	@JoinColumn(name="id_orden")
	private Orden orden;

	public RepuestoEmpleado() {
	}

	public long getIdRepuestoEmpleado() {
		return this.idRepuestoEmpleado;
	}

	public void setIdRepuestoEmpleado(long idRepuestoEmpleado) {
		this.idRepuestoEmpleado = idRepuestoEmpleado;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public InventarioRepuesto getInventarioRepuesto() {
		return this.inventarioRepuesto;
	}

	public void setInventarioRepuesto(InventarioRepuesto inventarioRepuesto) {
		this.inventarioRepuesto = inventarioRepuesto;
	}

	public Orden getOrden() {
		return this.orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

}