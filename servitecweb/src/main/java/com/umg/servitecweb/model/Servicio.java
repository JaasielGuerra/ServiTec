package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the servicio database table.
 * 
 */
@Entity
@Table(name = "servicio")
@NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s")
public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servicio")
	private int idServicio;

	private String descripcion;

	private int estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_commit")
	private Date fechaCommit;

	@Column(name = "hora_commit")
	private Time horaCommit;

	@Column(name = "precio_a")
	private double precioA;

	@Column(name = "precio_b")
	private double precioB;

	@Column(name = "precio_c")
	private double precioC;

	// bi-directional many-to-one association to CategoriaServicio
	@ManyToOne
	@JoinColumn(name = "id_categoria_servicio")
	private CategoriaServicio categoriaServicio;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	// bi-directional many-to-one association to ServicioAplicado
	@OneToMany
	private List<ServicioAplicado> servicioAplicados;
	
	public Servicio () {
		
	}

	public int getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
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

	public double getPrecioA() {
		return this.precioA;
	}

	public void setPrecioA(double precioA) {
		this.precioA = precioA;
	}

	public double getPrecioB() {
		return this.precioB;
	}

	public void setPrecioB(double precioB) {
		this.precioB = precioB;
	}

	public double getPrecioC() {
		return this.precioC;
	}

	public void setPrecioC(double precioC) {
		this.precioC = precioC;
	}

	public CategoriaServicio getCategoriaServicio() {
		return this.categoriaServicio;
	}

	public void setCategoriaServicio(CategoriaServicio categoriaServicio) {
		this.categoriaServicio = categoriaServicio;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ServicioAplicado> getServicioAplicados() {
		return this.servicioAplicados;
	}

	public void setServicioAplicados(List<ServicioAplicado> servicioAplicados) {
		this.servicioAplicados = servicioAplicados;
	}

	public ServicioAplicado addServicioAplicado(ServicioAplicado servicioAplicado) {
		getServicioAplicados().add(servicioAplicado);
		servicioAplicado.setServicio(this);

		return servicioAplicado;
	}

	public ServicioAplicado removeServicioAplicado(ServicioAplicado servicioAplicado) {
		getServicioAplicados().remove(servicioAplicado);
		servicioAplicado.setServicio(null);

		return servicioAplicado;
	}

}