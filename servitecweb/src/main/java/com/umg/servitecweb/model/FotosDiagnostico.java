package com.umg.servitecweb.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fotos_diagnostico database table.
 * 
 */
@Entity
@Table(name="fotos_diagnostico")
@NamedQuery(name="FotosDiagnostico.findAll", query="SELECT f FROM FotosDiagnostico f")
public class FotosDiagnostico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_fotos_diagnostico")
	private long idFotosDiagnostico;

	private String descripcion;

	private int estado;

	@Lob
	@Column(name="imagen", length = 65535)
	private byte[] imagen;

	//bi-directional many-to-one association to Orden
	@ManyToOne
	@JoinColumn(name="id_orden")
	private Orden orden;

	public FotosDiagnostico() {
	}

	public long getIdFotosDiagnostico() {
		return this.idFotosDiagnostico;
	}

	public void setIdFotosDiagnostico(long idFotosDiagnostico) {
		this.idFotosDiagnostico = idFotosDiagnostico;
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

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Orden getOrden() {
		return this.orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

}