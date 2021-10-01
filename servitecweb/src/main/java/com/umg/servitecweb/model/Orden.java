package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orden database table.
 * 
 */
@Entity
@Table(name="orden")
@NamedQuery(name="Orden.findAll", query="SELECT o FROM Orden o")
public class Orden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_orden")
	private long idOrden;

	private int aplicable;

	@Column(name="costo_extra")
	private double costoExtra;

	@Column(name="descripcion_extra")
	private String descripcionExtra;

	@Column(name="descripcion_orden")
	private String descripcionOrden;

	@Column(name="descripcion_recibido")
	private String descripcionRecibido;

	@Column(name="diagnostico_tecnico")
	private String diagnosticoTecnico;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_commit")
	private Date fechaCommit;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_entrega")
	private Date fechaEntrega;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_maxima_entrega")
	private Date fechaMaximaEntrega;

	@Temporal(TemporalType.DATE)
	@Column(name="hora_commit")
	private Date horaCommit;

	@Column(name="imagen_referencia")
	private String imagenReferencia;

	@Column(name="total_costo_servicio")
	private double totalCostoServicio;

	//bi-directional many-to-one association to FotosDiagnostico
	@OneToMany(mappedBy="orden")
	private List<FotosDiagnostico> fotosDiagnosticos;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to EstadoOrden
	@ManyToOne
	@JoinColumn(name="id_estado_orden")
	private EstadoOrden estadoOrden;

	//bi-directional many-to-one association to MotivoOrden
	@ManyToOne
	@JoinColumn(name="id_motivo_orden")
	private MotivoOrden motivoOrden;

	//bi-directional many-to-one association to Prioridad
	@ManyToOne
	@JoinColumn(name="id_prioridad")
	private Prioridad prioridad;

	//bi-directional many-to-one association to Tecnico
	@ManyToOne
	@JoinColumn(name="id_tecnico")
	private Tecnico tecnico;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to RepuestoEmpleado
	@OneToMany(mappedBy="orden")
	private List<RepuestoEmpleado> repuestoEmpleados;

	//bi-directional many-to-one association to ServicioAplicado
	@OneToMany(mappedBy="orden")
	private List<ServicioAplicado> servicioAplicados;

	public Orden() {
	}

	public long getIdOrden() {
		return this.idOrden;
	}

	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}

	public int getAplicable() {
		return this.aplicable;
	}

	public void setAplicable(int aplicable) {
		this.aplicable = aplicable;
	}

	public double getCostoExtra() {
		return this.costoExtra;
	}

	public void setCostoExtra(double costoExtra) {
		this.costoExtra = costoExtra;
	}

	public String getDescripcionExtra() {
		return this.descripcionExtra;
	}

	public void setDescripcionExtra(String descripcionExtra) {
		this.descripcionExtra = descripcionExtra;
	}

	public String getDescripcionOrden() {
		return this.descripcionOrden;
	}

	public void setDescripcionOrden(String descripcionOrden) {
		this.descripcionOrden = descripcionOrden;
	}

	public String getDescripcionRecibido() {
		return this.descripcionRecibido;
	}

	public void setDescripcionRecibido(String descripcionRecibido) {
		this.descripcionRecibido = descripcionRecibido;
	}

	public String getDiagnosticoTecnico() {
		return this.diagnosticoTecnico;
	}

	public void setDiagnosticoTecnico(String diagnosticoTecnico) {
		this.diagnosticoTecnico = diagnosticoTecnico;
	}

	public Date getFechaCommit() {
		return this.fechaCommit;
	}

	public void setFechaCommit(Date fechaCommit) {
		this.fechaCommit = fechaCommit;
	}

	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaMaximaEntrega() {
		return this.fechaMaximaEntrega;
	}

	public void setFechaMaximaEntrega(Date fechaMaximaEntrega) {
		this.fechaMaximaEntrega = fechaMaximaEntrega;
	}

	public Date getHoraCommit() {
		return this.horaCommit;
	}

	public void setHoraCommit(Date horaCommit) {
		this.horaCommit = horaCommit;
	}

	public String getImagenReferencia() {
		return this.imagenReferencia;
	}

	public void setImagenReferencia(String imagenReferencia) {
		this.imagenReferencia = imagenReferencia;
	}

	public double getTotalCostoServicio() {
		return this.totalCostoServicio;
	}

	public void setTotalCostoServicio(double totalCostoServicio) {
		this.totalCostoServicio = totalCostoServicio;
	}

	public List<FotosDiagnostico> getFotosDiagnosticos() {
		return this.fotosDiagnosticos;
	}

	public void setFotosDiagnosticos(List<FotosDiagnostico> fotosDiagnosticos) {
		this.fotosDiagnosticos = fotosDiagnosticos;
	}

	public FotosDiagnostico addFotosDiagnostico(FotosDiagnostico fotosDiagnostico) {
		getFotosDiagnosticos().add(fotosDiagnostico);
		fotosDiagnostico.setOrden(this);

		return fotosDiagnostico;
	}

	public FotosDiagnostico removeFotosDiagnostico(FotosDiagnostico fotosDiagnostico) {
		getFotosDiagnosticos().remove(fotosDiagnostico);
		fotosDiagnostico.setOrden(null);

		return fotosDiagnostico;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EstadoOrden getEstadoOrden() {
		return this.estadoOrden;
	}

	public void setEstadoOrden(EstadoOrden estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public MotivoOrden getMotivoOrden() {
		return this.motivoOrden;
	}

	public void setMotivoOrden(MotivoOrden motivoOrden) {
		this.motivoOrden = motivoOrden;
	}

	public Prioridad getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public Tecnico getTecnico() {
		return this.tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
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
		repuestoEmpleado.setOrden(this);

		return repuestoEmpleado;
	}

	public RepuestoEmpleado removeRepuestoEmpleado(RepuestoEmpleado repuestoEmpleado) {
		getRepuestoEmpleados().remove(repuestoEmpleado);
		repuestoEmpleado.setOrden(null);

		return repuestoEmpleado;
	}

	public List<ServicioAplicado> getServicioAplicados() {
		return this.servicioAplicados;
	}

	public void setServicioAplicados(List<ServicioAplicado> servicioAplicados) {
		this.servicioAplicados = servicioAplicados;
	}

	public ServicioAplicado addServicioAplicado(ServicioAplicado servicioAplicado) {
		getServicioAplicados().add(servicioAplicado);
		servicioAplicado.setOrden(this);

		return servicioAplicado;
	}

	public ServicioAplicado removeServicioAplicado(ServicioAplicado servicioAplicado) {
		getServicioAplicados().remove(servicioAplicado);
		servicioAplicado.setOrden(null);

		return servicioAplicado;
	}

}