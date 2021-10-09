package com.umg.servitecweb.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
import com.umg.servitecweb.model.Servicio;
import com.umg.servitecweb.model.Usuario;
import com.umg.servitecweb.repository.ICategoriaServicioRepo;
import com.umg.servitecweb.repository.IServicioRepo;
import com.umg.servitecweb.repository.IUsuarioRepo;

@CrossOrigin
@RestController
@RequestMapping("/serviciostecnicos")
public class RestServiciosTecnicosController {

	private static final Logger log = LoggerFactory.getLogger(RestServiciosTecnicosController.class);

	@Autowired
	private ICategoriaServicioRepo categoriaServicioRepo;
	@Autowired
	private IServicioRepo servicioRepo;
	@Autowired
	private IUsuarioRepo usuarioRepo;

	/**
	 * Crear categorias
	 * 
	 * @param cs
	 * @return
	 */
	@PostMapping("/categorias")
	public CategoriaServicio crearCategoriaServicio(@RequestBody CategoriaServicio cs) {

		cs.setEstado(1); // activo

		CategoriaServicio c = categoriaServicioRepo.save(cs);

		log.info("nueva categoria registrada");

		return c;
	}

	/**
	 * consultar categorias por estado
	 * 
	 * @param status
	 * @return
	 */

	@GetMapping("/categorias")
	public List<CategoriaServicio> consultarCategorias(
			@RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {

		log.debug("consultando por status: " + status);

		List<CategoriaServicio> cats = categoriaServicioRepo.findByEstado(status);

		return cats;
	}

	@GetMapping("/categorias/{id}")
	public CategoriaServicio obtenerCategoria(@PathVariable Integer id) {

		log.debug("consultando por id: " + id);

		CategoriaServicio c = categoriaServicioRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));;

		return c;
	}

	@PutMapping("/categorias/{id}")
	public CategoriaServicio actualizarCategoria(@RequestBody CategoriaServicio categ, @PathVariable Integer id) {

		return categoriaServicioRepo.findById(id).map(c -> {
			c.setDescripcion(categ.getDescripcion());
			return categoriaServicioRepo.save(c);
		}).orElseGet(() -> { // sino existe se crea una nueva
			CategoriaServicio c = categ;
			c.setEstado(1);
			return categoriaServicioRepo.save(c);
		});
	}

	@DeleteMapping("/categorias/{id}")
	public void eliminarCategoria(@PathVariable Integer id) {

		categoriaServicioRepo.findById(id).map(c -> { // se cambia a estado 0 eliminado
			c.setEstado(0);
			return categoriaServicioRepo.save(c);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}

	@PostMapping
	public Servicio crearServicio(@RequestBody Servicio s,
			@RequestParam(value = "idcategoria", required = true) Integer idCate,
			@RequestParam(value = "idusuario", required = true) Integer idUser) {

		// obtener entidades relacionadas o en su caso lanzar un mensaje de que no
		// existe el recruso
		CategoriaServicio c = categoriaServicioRepo.findById(idCate)
				.orElseThrow(() -> new RecursoNoEncontradoException(idCate.toString()));
		Usuario u = usuarioRepo.findById(idUser).orElseThrow(() -> new RecursoNoEncontradoException(idUser.toString()));

		Servicio ser = s;
		ser.setCategoriaServicio(c);
		ser.setUsuario(u);
		ser.setEstado(1);
		ser.setFechaCommit(Date.valueOf(LocalDate.now()));
		ser.setHoraCommit(Time.valueOf(LocalTime.now()));

		log.info("Nuevo servicio creado");

		return servicioRepo.save(ser);
	}

	@GetMapping
	public List<Servicio> consultar(
			@RequestParam(value = "status", defaultValue = "1", required = false) Integer status) {

		List<Servicio> l = servicioRepo.findByEstado(status);

		return l;
	}

	@GetMapping("/{id}")
	public Servicio obtenerServicio(@PathVariable Integer id) {

		Servicio l = servicioRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

		return l;
	}

	
	@PutMapping("/{id}")
	public Servicio actualizarServicio(@RequestBody Servicio s,
			@RequestParam(value = "idcategoria", required = true) Integer idCate,
			@RequestParam(value = "idusuario", required = true) Integer idUser, @PathVariable Integer id) {

		// obtener entidades relacionadas o en su caso lanzar un mensaje de que no
		// existe el recruso
		CategoriaServicio c = categoriaServicioRepo.findById(idCate)
				.orElseThrow(() -> new RecursoNoEncontradoException(idCate.toString()));
		Usuario u = usuarioRepo.findById(idUser).orElseThrow(() -> new RecursoNoEncontradoException(idUser.toString()));

		Servicio servicio = servicioRepo.findById(id).map(ser -> { // se obtiene el servicio y se actualizan los campos

			ser.setDescripcion(s.getDescripcion());
			ser.setPrecioA(s.getPrecioA());
			ser.setPrecioB(s.getPrecioB());
			ser.setPrecioC(s.getPrecioC());
			ser.setCategoriaServicio(c);

			return servicioRepo.save(ser);

		}).orElseGet(() -> { // sino existe el recurso a actualizar, entonces se crea uno

			Servicio ser = s;
			ser.setCategoriaServicio(c);
			ser.setUsuario(u);
			ser.setEstado(1);
			ser.setFechaCommit(Date.valueOf(LocalDate.now()));
			ser.setHoraCommit(Time.valueOf(LocalTime.now()));

			return servicioRepo.save(ser);
		});

		log.info("Servicio actualizado con exito");

		return servicio;
	}
	
	@DeleteMapping("/{id}")
	public void eliminarServicio(@PathVariable Integer id) {

		servicioRepo.findById(id).map(s -> { // se cambia a estado 0 eliminado
			s.setEstado(0);
			return servicioRepo.save(s);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}

}
