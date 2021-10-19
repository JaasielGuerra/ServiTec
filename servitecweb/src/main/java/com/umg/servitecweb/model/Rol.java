package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@Table(name="rol")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_rol")
	private int idRol;

	@Column(name="atender_cobros")
	private int atenderCobros;

	@Column(name="cobrar_servicios")
	private int cobrarServicios;

	@Column(name="control_usuarios")
	private int controlUsuarios;

	private int estado;

	@Column(name="generar_ordenes")
	private int generarOrdenes;

	@Column(name="generar_reportes")
	private int generarReportes;

	private int graficas;

	@Column(name="inventario_repuestos")
	private int inventarioRepuestos;

	@Column(name="nombre_rol")
	private String nombreRol;

	@Column(name="personal_tecnico")
	private int personalTecnico;

	@Column(name="servicios_tecnicos")
	private int serviciosTecnicos;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="rol")
	private List<Usuario> usuarios;

	public Rol() {
	}

	public int getIdRol() {
		return this.idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getAtenderCobros() {
		return this.atenderCobros;
	}

	public void setAtenderCobros(int atenderCobros) {
		this.atenderCobros = atenderCobros;
	}

	public int getCobrarServicios() {
		return this.cobrarServicios;
	}

	public void setCobrarServicios(int cobrarServicios) {
		this.cobrarServicios = cobrarServicios;
	}

	public int getControlUsuarios() {
		return this.controlUsuarios;
	}

	public void setControlUsuarios(int controlUsuarios) {
		this.controlUsuarios = controlUsuarios;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getGenerarOrdenes() {
		return this.generarOrdenes;
	}

	public void setGenerarOrdenes(int generarOrdenes) {
		this.generarOrdenes = generarOrdenes;
	}

	public int getGenerarReportes() {
		return this.generarReportes;
	}

	public void setGenerarReportes(int generarReportes) {
		this.generarReportes = generarReportes;
	}

	public int getGraficas() {
		return this.graficas;
	}

	public void setGraficas(int graficas) {
		this.graficas = graficas;
	}

	public int getInventarioRepuestos() {
		return this.inventarioRepuestos;
	}

	public void setInventarioRepuestos(int inventarioRepuestos) {
		this.inventarioRepuestos = inventarioRepuestos;
	}

	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public int getPersonalTecnico() {
		return this.personalTecnico;
	}

	public void setPersonalTecnico(int personalTecnico) {
		this.personalTecnico = personalTecnico;
	}

	public int getServiciosTecnicos() {
		return this.serviciosTecnicos;
	}

	public void setServiciosTecnicos(int serviciosTecnicos) {
		this.serviciosTecnicos = serviciosTecnicos;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setRol(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setRol(null);

		return usuario;
	}

}