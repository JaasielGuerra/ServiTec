package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tecnico database table.
 * 
 */
@Entity
@Table(name="tecnico")
@NamedQuery(name="Tecnico.findAll", query="SELECT t FROM Tecnico t")
public class Tecnico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tecnico")
	private int idTecnico;

	private int estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_commit")
	private Date fechaCommit;

	@Column(name="hora_commit")
	private Time horaCommit;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	//bi-directional many-to-one association to Orden
	@OneToMany(mappedBy="tecnico")
	private List<Orden> ordens;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Tecnico() {
	}

	public int getIdTecnico() {
		return this.idTecnico;
	}

	public void setIdTecnico(int idTecnico) {
		this.idTecnico = idTecnico;
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

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public List<Orden> getOrdens() {
		return this.ordens;
	}

	public void setOrdens(List<Orden> ordens) {
		this.ordens = ordens;
	}

	public Orden addOrden(Orden orden) {
		getOrdens().add(orden);
		orden.setTecnico(this);

		return orden;
	}

	public Orden removeOrden(Orden orden) {
		getOrdens().remove(orden);
		orden.setTecnico(null);

		return orden;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}