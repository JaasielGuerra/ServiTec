package com.umg.servitecweb.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
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
import com.umg.servitecweb.model.EstadoOrden;
import com.umg.servitecweb.model.Tecnico;
import com.umg.servitecweb.model.Usuario;
import com.umg.servitecweb.repository.IEstadoOrdenRepo;
import com.umg.servitecweb.repository.IOrdenRepo;
import com.umg.servitecweb.repository.ITecnicoRepo;
import com.umg.servitecweb.repository.IUsuarioRepo;

@CrossOrigin
@RestController
@RequestMapping("/tecnicos")
public class RestTecnicosController {

	private static final Logger log = LoggerFactory.getLogger(RestTecnicosController.class);

	@Autowired
	private ITecnicoRepo tecnicoRepo;
	@Autowired
	private IUsuarioRepo usuarioRepo;
	@Autowired
	private IOrdenRepo ordenRepo;
	@Autowired
	private IEstadoOrdenRepo estadoOrdenRepo;

	@PostMapping
	public Tecnico crear(@RequestBody Tecnico t, @RequestParam(value = "idusuario", required = true) Integer idUser) {

		// obtener entidades relacionadas o en su caso lanzar un mensaje de que no
		// existe el recruso
		Usuario u = usuarioRepo.findById(idUser).orElseThrow(() -> new RecursoNoEncontradoException(idUser.toString()));

		t.setUsuario(u);
		t.setEstado(1);
		t.setFechaCommit(Date.valueOf(LocalDate.now()));
		t.setHoraCommit(Time.valueOf(LocalTime.now()));

		log.info("Nuevo tecnico creado");

		return tecnicoRepo.save(t);
	}

	@GetMapping
	public List<Tecnico> consultar(
			@RequestParam(value = "status", defaultValue = "1", required = false) Integer status) {

		List<Tecnico> l = tecnicoRepo.findByEstado(status);

		return l;
	}

	@GetMapping("/{id}")
	public Tecnico obtener(@PathVariable Integer id) {

		Tecnico l = tecnicoRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

		return l;
	}

	@PutMapping("/{id}")
	public Tecnico actualizar(@RequestBody Tecnico t,
			@RequestParam(value = "idusuario", required = true) Integer idUser, @PathVariable Integer id) {

		// obtener entidades relacionadas o en su caso lanzar un mensaje de que no
		// existe el recruso

		Usuario u = usuarioRepo.findById(idUser).orElseThrow(() -> new RecursoNoEncontradoException(idUser.toString()));

		Tecnico tecnico = tecnicoRepo.findById(id).map(te -> {

			te.setNombreCompleto(t.getNombreCompleto());
			te.setUsuario(u);

			return tecnicoRepo.save(te);

		}).orElseGet(() -> { // sino existe el recurso a actualizar, entonces se crea uno

			Tecnico tec = t;
			tec.setIdTecnico(0);
			tec.setUsuario(u);
			tec.setEstado(1);
			tec.setFechaCommit(Date.valueOf(LocalDate.now()));
			tec.setHoraCommit(Time.valueOf(LocalTime.now()));

			return tecnicoRepo.save(tec);
		});

		log.info("tecnico actualizado con exito");

		return tecnico;
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {

		tecnicoRepo.findById(id).map(t -> { // se cambia a estado 0 eliminado
			t.setEstado(0);
			return tecnicoRepo.save(t);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}

	@GetMapping("/{id}/disponibilidad")
	private Long dispobilidadTecnico(@PathVariable Integer id) {

		Tecnico t = tecnicoRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

		Collection<EstadoOrden> e = estadoOrdenRepo.findByIdEstadoOrdenIn(Arrays.asList(1, 2, 3));

		return ordenRepo.countByTecnicoAndEstadoOrdenIn(t, e);
	}

}
