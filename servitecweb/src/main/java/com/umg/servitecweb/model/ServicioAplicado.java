package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the servicio_aplicado database table.
 * 
 */
@Entity
@Table(name="servicio_aplicado")
@NamedQuery(name="ServicioAplicado.findAll", query="SELECT s FROM ServicioAplicado s")
public class ServicioAplicado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_servicio_aplicado")
	private long idServicioAplicado;

	private int cantidad;

	private double precio;

	//bi-directional many-to-one association to Orden
	@ManyToOne
	@JoinColumn(name="id_orden")
	private Orden orden;

	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="id_servicio")
	private Servicio servicio;

	public ServicioAplicado() {
		
	}

	public long getIdServicioAplicado() {
		return this.idServicioAplicado;
	}

	public void setIdServicioAplicado(long idServicioAplicado) {
		this.idServicioAplicado = idServicioAplicado;
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

	public Orden getOrden() {
		return this.orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

}