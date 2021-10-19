package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int idUsuario;

	private int estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_commit")
	private Date fechaCommit;

	@Column(name = "hora_commit")
	private Time horaCommit;

	private String nombre;

	private String password;

	private String user;

	// bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy = "usuario")
	private List<Cliente> clientes;

	// bi-directional many-to-one association to InventarioRepuesto
	@OneToMany(mappedBy = "usuario")
	private List<InventarioRepuesto> inventarioRepuestos;

	// bi-directional many-to-one association to Orden
	@OneToMany(mappedBy = "usuario")
	private List<Orden> ordens;

	// bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy = "usuario")
	private List<Servicio> servicios;

	// bi-directional many-to-one association to Tecnico
	@OneToMany(mappedBy = "usuario")
	private List<Tecnico> tecnicos;

	// bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "id_rol")
	private Rol rol;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaCommit() {
		return this.fechaCommit;
	}

	public void setFechaCommit(Date fechaCommit) {
		this.fechaCommit = fechaCommit;
	}

	public Time getHoraCommit() {
		return this.horaCommit;
	}

	public void setHoraCommit(Time horaCommit) {
		this.horaCommit = horaCommit;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setUsuario(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setUsuario(null);

		return cliente;
	}

	public List<InventarioRepuesto> getInventarioRepuestos() {
		return this.inventarioRepuestos;
	}

	public void setInventarioRepuestos(List<InventarioRepuesto> inventarioRepuestos) {
		this.inventarioRepuestos = inventarioRepuestos;
	}

	public InventarioRepuesto addInventarioRepuesto(InventarioRepuesto inventarioRepuesto) {
		getInventarioRepuestos().add(inventarioRepuesto);
		inventarioRepuesto.setUsuario(this);

		return inventarioRepuesto;
	}

	public InventarioRepuesto removeInventarioRepuesto(InventarioRepuesto inventarioRepuesto) {
		getInventarioRepuestos().remove(inventarioRepuesto);
		inventarioRepuesto.setUsuario(null);

		return inventarioRepuesto;
	}

	public List<Orden> getOrdens() {
		return this.ordens;
	}

	public void setOrdens(List<Orden> ordens) {
		this.ordens = ordens;
	}

	public Orden addOrden(Orden orden) {
		getOrdens().add(orden);
		orden.setUsuario(this);

		return orden;
	}

	public Orden removeOrden(Orden orden) {
		getOrdens().remove(orden);
		orden.setUsuario(null);

		return orden;
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio addServicio(Servicio servicio) {
		getServicios().add(servicio);
		servicio.setUsuario(this);

		return servicio;
	}

	public Servicio removeServicio(Servicio servicio) {
		getServicios().remove(servicio);
		servicio.setUsuario(null);

		return servicio;
	}

	public List<Tecnico> getTecnicos() {
		return this.tecnicos;
	}

	public void setTecnicos(List<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public Tecnico addTecnico(Tecnico tecnico) {
		getTecnicos().add(tecnico);
		tecnico.setUsuario(this);

		return tecnico;
	}

	public Tecnico removeTecnico(Tecnico tecnico) {
		getTecnicos().remove(tecnico);
		tecnico.setUsuario(null);

		return tecnico;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}