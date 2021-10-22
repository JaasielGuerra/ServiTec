package com.umg.servitecweb.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.umg.servitecweb.exception.RecursoNoEncontradoException;
import com.umg.servitecweb.model.Cliente;
import com.umg.servitecweb.model.EstadoOrden;
import com.umg.servitecweb.model.Mensaje;
import com.umg.servitecweb.model.Tecnico;
import com.umg.servitecweb.model.Usuario;
import com.umg.servitecweb.repository.IClienteRepo;
import com.umg.servitecweb.repository.IUsuarioRepo;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class RestUsuariosController {

	private static final Logger log = LoggerFactory.getLogger(RestUsuariosController.class);

	@Autowired
	private IUsuarioRepo usuarioRepo;

	@GetMapping
	public List<Usuario> consultar(
			@RequestParam(value = "status", defaultValue = "1", required = false) Integer status) {
		List<Usuario> l = usuarioRepo.findByEstado(status);
		return l;
	}

	@GetMapping("/{id}")
	public Usuario obtenerPorId(@PathVariable Integer id) {
		Usuario l = usuarioRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		return l;
	}

}
