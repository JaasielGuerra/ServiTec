package com.umg.servitecweb.exception;

public class RecursoNoEncontradoException extends RuntimeException{

	public RecursoNoEncontradoException(String idRecurso) {
		// TODO Auto-generated constructor stub
		super("No se puede encontrar el recurso con id " + idRecurso);
	}
	
}
