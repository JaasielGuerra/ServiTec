package com.umg.servitecweb.service;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.umg.servitecweb.model.EstadoOrden;
import com.umg.servitecweb.model.MotivoOrden;
import com.umg.servitecweb.model.Prioridad;
import com.umg.servitecweb.repository.IEstadoOrdenRepo;
import com.umg.servitecweb.repository.IMotivoOrdenRepo;
import com.umg.servitecweb.repository.IPrioridadRepo;

@CrossOrigin
@RestController
@RequestMapping("/estadosordenes")
public class RestEstadoOrdenController {

	private static final Logger log = LoggerFactory.getLogger(RestEstadoOrdenController.class);

	@Autowired
	private IEstadoOrdenRepo repo;

	@GetMapping
	public List<EstadoOrden> consultar(@RequestParam(value = "status", required = true) Integer status) {

		List estados = repo.findByEstado(status);

		log.debug("consultando por estado: " + status);

		return estados;
	}

	@GetMapping("/atender")
	public List<EstadoOrden> consultarEstadosAtender() {

		List estados = repo.findByIdEstadoOrdenIn(Arrays.asList(1, 2, 3));

		return estados;
	}

	@GetMapping("/{id}")
	public EstadoOrden obtener(@PathVariable Integer id) {

		EstadoOrden e = repo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

		log.debug("consultando por id: " + id);

		return e;
	}

}
