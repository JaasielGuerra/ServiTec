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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umg.servitecweb.exception.RecursoNoEncontradoException;
import com.umg.servitecweb.model.CategoriaServicio;
import com.umg.servitecweb.model.MotivoOrden;
import com.umg.servitecweb.repository.IMotivoOrdenRepo;

@CrossOrigin
@RestController
@RequestMapping("/motivosorden")
public class RestMotivosOrdenController {

	private static final Logger log = LoggerFactory.getLogger(RestMotivosOrdenController.class);

	@Autowired
	private IMotivoOrdenRepo repo;

	@PostMapping
	public MotivoOrden crear(@RequestBody MotivoOrden m) {

		m.setEstado(1);

		MotivoOrden m1 = repo.save(m);

		log.info("nueva categoria registrada");

		return m;
	}

	@PutMapping("/{id}")
	public MotivoOrden actualizar(@RequestBody MotivoOrden m, @PathVariable Integer id) {

		return repo.findById(id).map(m_ -> {
			m_.setDescripcion(m.getDescripcion());
			log.info("categoria actualizada");
			return repo.save(m_);
		}).orElseGet(() -> { // sino existe se crea una nueva
			MotivoOrden mot = m;
			mot.setIdMotivoOrden(0);
			mot.setEstado(1);
			log.info("nueva categoria registrada");
			return repo.save(mot);
		});

	}

	@GetMapping
	public List<MotivoOrden> consultar(
			@RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {

		List motivos = repo.findByEstado(status);

		log.debug("consultando por estado: " + status);

		return motivos;
	}

	@GetMapping("/{id}")
	public MotivoOrden obtener(@PathVariable Integer id) {

		MotivoOrden m = repo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

		log.debug("consultando por id: " + id);

		return m;
	}

	@DeleteMapping("/{id}")
	public MotivoOrden eliminar(@PathVariable Integer id) {

		return repo.findById(id).map(m -> {

			m.setEstado(0);

			return repo.save(m);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

	}

}
