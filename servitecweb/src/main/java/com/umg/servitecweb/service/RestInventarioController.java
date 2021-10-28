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
import com.umg.servitecweb.model.Caja;
import com.umg.servitecweb.model.Estante;
import com.umg.servitecweb.model.InventarioRepuesto;
import com.umg.servitecweb.model.Servicio;
import com.umg.servitecweb.model.Ubicacion;
import com.umg.servitecweb.model.Usuario;
import com.umg.servitecweb.repository.ICajaRepo;
import com.umg.servitecweb.repository.IEstanteRepo;
import com.umg.servitecweb.repository.IInventarioRepo;
import com.umg.servitecweb.repository.IUbicacionRepo;
import com.umg.servitecweb.repository.IUsuarioRepo;

@CrossOrigin
@RestController
@RequestMapping("/repuestos")
public class RestInventarioController {

	private static final Logger log = LoggerFactory.getLogger(RestMotivosOrdenController.class);

	@Autowired
	private ICajaRepo cajaRepo;
	@Autowired
	private IEstanteRepo estanteRepo;
	@Autowired
	private IUbicacionRepo ubicacionRepo;
	@Autowired
	private IInventarioRepo inventarioRepo;
	@Autowired
	private IUsuarioRepo usuarioRepo;

	// --------------- cajas-------------------
	@PostMapping("/cajas")
	public Caja crearCaja(@RequestBody Caja c) {
		c.setEstado(1);
		log.info("nueva caja registrada");
		return cajaRepo.save(c);
	}

	@PutMapping("/cajas/{id}")
	public Caja actualizarCaja(@RequestBody Caja c, @PathVariable Integer id) {
		return cajaRepo.findById(id).map(c1 -> {
			c1.setDescripcion(c.getDescripcion());
			c1.setNumero(c.getNumero());
			log.info("caja actualizada");
			return cajaRepo.save(c1);
		}).orElseGet(() -> { // sino existe se crea una nueva
			Caja caja = c;
			caja.setIdCaja(0);
			caja.setEstado(1);
			log.info("nueva caja registrada");
			return cajaRepo.save(caja);
		});
	}

	@GetMapping("/cajas")
	public List<Caja> consultarCajas(
			@RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
		List<Caja> cajas = cajaRepo.findByEstado(status);
		log.debug("consultando por estado: " + status);
		return cajas;
	}

	@GetMapping("/cajas/{id}")
	public Caja obtenerCaja(@PathVariable Integer id) {
		Caja caja = cajaRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		log.debug("consultando por id: " + id);
		return caja;
	}

	@DeleteMapping("/cajas/{id}")
	public Caja eliminarCaja(@PathVariable Integer id) {
		return cajaRepo.findById(id).map(c -> {
			c.setEstado(0);
			log.info("recurso id = " + id + " eliminado");
			return cajaRepo.save(c);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}

	// --------------- estantes -------------------

	@PostMapping("/estantes")
	public Estante crearEstante(@RequestBody Estante e) {
		e.setEstado(1);
		Estante e1 = estanteRepo.save(e);
		log.info("nuevo estante");
		return e1;
	}

	@PutMapping("/estantes/{id}")
	public Estante actualizarEstante(@RequestBody Estante e, @PathVariable Integer id) {
		return estanteRepo.findById(id).map(e1 -> {
			e1.setDescripcion(e.getDescripcion());
			e1.setNumero(e.getNumero());
			log.info("estante actualizado");
			return estanteRepo.save(e1);
		}).orElseGet(() -> { // sino existe se crea una nueva
			Estante estante = e;
			estante.setIdEstante(0);
			estante.setEstado(1);
			log.info("nuevo estante registrado");
			return estanteRepo.save(estante);
		});
	}

	@GetMapping("/estantes")
	public List<Estante> consultarEstantes(
			@RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
		List<Estante> list = estanteRepo.findByEstado(status);
		log.debug("consultando por estado: " + status);
		return list;
	}

	@GetMapping("/estantes/{id}")
	public Estante obtenerEstante(@PathVariable Integer id) {
		Estante estante = estanteRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		log.debug("consultando por id: " + id);
		return estante;
	}

	@DeleteMapping("/estantes/{id}")
	public Estante eliminarEstante(@PathVariable Integer id) {
		return estanteRepo.findById(id).map(e -> {
			e.setEstado(0);
			log.info("recurso id = " + id + " eliminado");
			return estanteRepo.save(e);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}

	// --------------- ubicaciones -------------------

	@PostMapping("/ubicaciones")
	public Ubicacion crearUbicacion(@RequestBody Ubicacion u) {
		u.setEstado(1);
		log.info("nuevo ubicacion");
		return ubicacionRepo.save(u);
	}

	@PutMapping("/ubicaciones/{id}")
	public Ubicacion actualizarUbicacion(@RequestBody Ubicacion u, @PathVariable Integer id) {
		return ubicacionRepo.findById(id).map(ub -> {
			ub.setDescripcion(u.getDescripcion());
			log.info("ubicacion actualizado");
			return ubicacionRepo.save(ub);
		}).orElseGet(() -> { // sino existe se crea una nueva
			Ubicacion ubicacion = u;
			ubicacion.setIdUbicacion(0);
			ubicacion.setEstado(1);
			log.info("nuevo ubicacion registrado");
			return ubicacionRepo.save(ubicacion);
		});
	}

	@GetMapping("/ubicaciones")
	public List<Ubicacion> consultarUbicaciones(
			@RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
		List<Ubicacion> list = ubicacionRepo.findByEstado(status);
		log.debug("consultando por estado: " + status);
		return list;
	}

	@GetMapping("/ubicaciones/{id}")
	public Ubicacion obtenerUbicacion(@PathVariable Integer id) {
		Ubicacion ubicacion = ubicacionRepo.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		log.debug("consultando por id: " + id);
		return ubicacion;
	}

	@DeleteMapping("/ubicaciones/{id}")
	public Ubicacion eliminarUbicacion(@PathVariable Integer id) {
		return ubicacionRepo.findById(id).map(u -> {
			u.setEstado(0);
			log.info("recurso id = " + id + " eliminado");
			return ubicacionRepo.save(u);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}

	/// ---------- inventario---------------
	@PostMapping
	public InventarioRepuesto crear(@RequestBody InventarioRepuesto ir,
			@RequestParam(value = "idcaja", required = true) Integer idCaja,
			@RequestParam(value = "idestante", required = true) Integer idEstante,
			@RequestParam(value = "idubicacion", required = true) Integer idUbicacion,
			@RequestParam(value = "idusuario", required = true) Integer idUsuario) {

		// obtener entidades relacionadas o en su caso lanzar un mensaje de que no
		// existe el recruso
		Usuario user = usuarioRepo.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException(idUsuario.toString()));
		Caja caja = cajaRepo.findById(idCaja).orElseThrow(() -> new RecursoNoEncontradoException(idCaja.toString()));
		Estante estatnte = estanteRepo.findById(idEstante)
				.orElseThrow(() -> new RecursoNoEncontradoException(idEstante.toString()));
		Ubicacion ubicacion = ubicacionRepo.findById(idUbicacion)
				.orElseThrow(() -> new RecursoNoEncontradoException(idUbicacion.toString()));

		InventarioRepuesto inventario = ir;

		inventario.setEstante(estatnte);
		inventario.setUbicacion(ubicacion);
		inventario.setCaja(caja);
		inventario.setUsuario(user);
		inventario.setEstado(1);
		inventario.setFechaCommit(Date.valueOf(LocalDate.now()));
		inventario.setHoraCommit(Time.valueOf(LocalTime.now()));

		log.info("repuesto registrados");

		return inventarioRepo.save(inventario);
	}

	@PutMapping("/{id}")
	public InventarioRepuesto actualizar(@PathVariable Long id, @RequestBody InventarioRepuesto ir,
			@RequestParam(value = "idcaja", required = true) Integer idCaja,
			@RequestParam(value = "idestante", required = true) Integer idEstante,
			@RequestParam(value = "idubicacion", required = true) Integer idUbicacion) {

		// obtener entidades relacionadas o en su caso lanzar un mensaje de que no
		// existe el recruso
		Caja caja = cajaRepo.findById(idCaja).orElseThrow(() -> new RecursoNoEncontradoException(idCaja.toString()));
		Estante estante = estanteRepo.findById(idEstante)
				.orElseThrow(() -> new RecursoNoEncontradoException(idEstante.toString()));
		Ubicacion ubicacion = ubicacionRepo.findById(idUbicacion)
				.orElseThrow(() -> new RecursoNoEncontradoException(idUbicacion.toString()));

		return inventarioRepo.findById(id).map(i -> {

			ir.setEstante(estante);
			ir.setUbicacion(ubicacion);
			ir.setCaja(caja);

			log.info("repuesto actualizado");

			return inventarioRepo.save(ir);
		}).orElseGet(() -> {

			ir.setIdInventarioRepuesto(null);
			ir.setEstante(estante);
			ir.setUbicacion(ubicacion);
			ir.setCaja(caja);
			ir.setEstado(1);
			ir.setFechaCommit(Date.valueOf(LocalDate.now()));
			ir.setHoraCommit(Time.valueOf(LocalTime.now()));

			log.info("servicio creado");
			return inventarioRepo.save(ir);
		});
	}

	@PutMapping("/{id}/cambiarestado")
	public InventarioRepuesto cambiarEstadp(@PathVariable Long id, @RequestBody Integer estado) {

		return inventarioRepo.findById(id).map(i -> {

			i.setEstado(estado);

			log.info("repuesto actualizado");

			return inventarioRepo.save(i);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}

	@GetMapping
	public List<InventarioRepuesto> consultar(
			@RequestParam(value = "estante", defaultValue = "0", required = false) Integer estante,
			@RequestParam(value = "ubicacion", defaultValue = "0", required = false) Integer ubicacion,
			@RequestParam(value = "caja", defaultValue = "0", required = false) Integer caja) {

		Caja c = cajaRepo.findById(caja).orElseGet(() -> null);
		Estante e = estanteRepo.findById(estante).orElseGet(() -> null);
		Ubicacion u = ubicacionRepo.findById(ubicacion).orElseGet(() -> null);

		List<InventarioRepuesto> l = inventarioRepo.findByFilter(e, u, c);
		return l;
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		inventarioRepo.findById(id).map(i -> {
			i.setEstado(0); // eliminado
			return inventarioRepo.save(i);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}

	@GetMapping("/{id}")
	public InventarioRepuesto obtenerServicio(@PathVariable Long id) {
		InventarioRepuesto l = inventarioRepo.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		return l;
	}

	@PutMapping("/{id}/ajustarexistencia")
	public InventarioRepuesto ajustar(@PathVariable Long id, @RequestBody Integer existencia) {
		return inventarioRepo.findById(id).map(i -> {
			i.setExistencia(existencia);
			return inventarioRepo.save(i);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}

	@PutMapping("/{id}/agregarexistencia")
	public InventarioRepuesto agrgar(@PathVariable Long id, @RequestBody Integer cantidad) {
		return inventarioRepo.findById(id).map(i -> {
			i.setExistencia(i.getExistencia() + cantidad);
			return inventarioRepo.save(i);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}
}
