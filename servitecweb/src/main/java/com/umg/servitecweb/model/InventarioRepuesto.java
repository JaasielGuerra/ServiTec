package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inventario_repuesto database table.
 * 
 */
@Entity
@Table(name="inventario_repuesto")
@NamedQuery(name="InventarioRepuesto.findAll", query="SELECT i FROM InventarioRepuesto i")
public class InventarioRepuesto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_inventario_repuesto")
	private Long idInventarioRepuesto;

	private String descripcion;

	private int estado;

	private int existencia;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_commit")
	private Date fechaCommit;

	@Temporal(TemporalType.DATE)
	@Column(name="hora_commit")
	private Date horaCommit;

	private double precio;

	//bi-directional many-to-one association to Caja
	@ManyToOne
	@JoinColumn(name="id_caja")
	private Caja caja;

	//bi-directional many-to-one association to Estante
	@ManyToOne
	@JoinColumn(name="id_estante")
	private Estante estante;

	//bi-directional many-to-one association to Ubicacion
	@ManyToOne
	@JoinColumn(name="id_ubicacion")
	private Ubicacion ubicacion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to RepuestoEmpleado
	@OneToMany(mappedBy="inventarioRepuesto")
	private List<RepuestoEmpleado> repuestoEmpleados;

	public InventarioRepuesto() {
	}

	public Long getIdInventarioRepuesto() {
		return this.idInventarioRepuesto;
	}

	public void setIdInventarioRepuesto(Long idInventarioRepuesto) {
		this.idInventarioRepuesto = idInventarioRepuesto;
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

	public int getExistencia() {
		return this.existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	public Date getFechaCommit() {
		return this.fechaCommit;
	}

	public void setFechaCommit(Date fechaCommit) {
		this.fechaCommit = fechaCommit;
	}

	public Date getHoraCommit() {
		return this.horaCommit;
	}

	public void setHoraCommit(Date horaCommit) {
		this.horaCommit = horaCommit;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Caja getCaja() {
		return this.caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Estante getEstante() {
		return this.estante;
	}

	public void setEstante(Estante estante) {
		this.estante = estante;
	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<RepuestoEmpleado> getRepuestoEmpleados() {
		return this.repuestoEmpleados;
	}

	public void setRepuestoEmpleados(List<RepuestoEmpleado> repuestoEmpleados) {
		this.repuestoEmpleados = repuestoEmpleados;
	}

	public RepuestoEmpleado addRepuestoEmpleado(RepuestoEmpleado repuestoEmpleado) {
		getRepuestoEmpleados().add(repuestoEmpleado);
		repuestoEmpleado.setInventarioRepuesto(this);

		return repuestoEmpleado;
	}

	public RepuestoEmpleado removeRepuestoEmpleado(RepuestoEmpleado repuestoEmpleado) {
		getRepuestoEmpleados().remove(repuestoEmpleado);
		repuestoEmpleado.setInventarioRepuesto(null);

		return repuestoEmpleado;
	}

}