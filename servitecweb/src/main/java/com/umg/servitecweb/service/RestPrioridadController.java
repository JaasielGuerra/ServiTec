package com.umg.servitecweb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umg.servitecweb.exception.RecursoNoEncontradoException;
import com.umg.servitecweb.model.CategoriaServicio;
import com.umg.servitecweb.model.MotivoOrden;
import com.umg.servitecweb.model.Prioridad;
import com.umg.servitecweb.repository.IMotivoOrdenRepo;
import com.umg.servitecweb.repository.IPrioridadRepo;

@CrossOrigin
@RestController
@RequestMapping("/prioridades")
public class RestPrioridadController {

	private static final Logger log = LoggerFactory.getLogger(RestPrioridadController.class);

	@Autowired
	private IPrioridadRepo repo;

	@GetMapping
	public List<Prioridad> consultar(
			@RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {

		List prioridades = repo.findByEstado(status);

		log.debug("consultando por estado: " + status);

		return prioridades;
	}

	@GetMapping("/{id}")
	public Prioridad obtener(@PathVariable Integer id) {

		Prioridad p = repo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

		log.debug("consultando por id: " + id);

		return p;
	}

}
