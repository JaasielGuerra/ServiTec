package com.umg.servitecweb.model;

public class Mensaje {

	private String mensaje;
	private Integer errores;

	public Mensaje(String mensaje, Integer errores) {
		super();
		this.mensaje = mensaje;
		this.errores = errores;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Integer getErrores() {
		return errores;
	}

	public void setErrores(Integer errores) {
		this.errores = errores;
	}

}
