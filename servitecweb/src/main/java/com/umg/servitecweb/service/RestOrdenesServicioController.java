package com.umg.servitecweb.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umg.servitecweb.exception.RecursoNoEncontradoException;
import com.umg.servitecweb.model.Cliente;
import com.umg.servitecweb.model.EstadoOrden;
import com.umg.servitecweb.model.FotosDiagnostico;
import com.umg.servitecweb.model.InventarioRepuesto;
import com.umg.servitecweb.model.MotivoOrden;
import com.umg.servitecweb.model.Orden;
import com.umg.servitecweb.model.Prioridad;
import com.umg.servitecweb.model.RepuestoEmpleado;
import com.umg.servitecweb.model.ServicioAplicado;
import com.umg.servitecweb.model.Tecnico;
import com.umg.servitecweb.model.Usuario;
import com.umg.servitecweb.repository.IClienteRepo;
import com.umg.servitecweb.repository.IEstadoOrdenRepo;
import com.umg.servitecweb.repository.IFotosDiagnosticoRepo;
import com.umg.servitecweb.repository.IInventarioRepo;
import com.umg.servitecweb.repository.IMotivoOrdenRepo;
import com.umg.servitecweb.repository.IOrdenRepo;
import com.umg.servitecweb.repository.IPrioridadRepo;
import com.umg.servitecweb.repository.IRepuestoEmpleadoRepo;
import com.umg.servitecweb.repository.IServicioAplicadoRepo;
import com.umg.servitecweb.repository.ITecnicoRepo;
import com.umg.servitecweb.repository.IUsuarioRepo;

@CrossOrigin
@RestController
@RequestMapping("/ordenesservicio")
public class RestOrdenesServicioController {

	private static final Logger log = LoggerFactory.getLogger(RestOrdenesServicioController.class);

	@Autowired
	private IOrdenRepo ordenRepo;
	@Autowired
	private IUsuarioRepo usuarioRepo;
	@Autowired
	private ITecnicoRepo tecnicoRepo;
	@Autowired
	private IPrioridadRepo prioridadRepo;
	@Autowired
	private IMotivoOrdenRepo motivoOrdenRepo;
	@Autowired
	private IClienteRepo clienteRepo;
	@Autowired
	private IEstadoOrdenRepo estadoOrdenRepo;
	@Autowired
	private IRepuestoEmpleadoRepo repuestoEmpleadoRepo;
	@Autowired
	private IServicioAplicadoRepo servicioAplicadoRepo;
	@Autowired
	private IInventarioRepo inventarioRepo;
	@Autowired
	private IFotosDiagnosticoRepo fotosDiagnosticoRepo;

	@GetMapping
	public List<Orden> consultar(@RequestParam(value = "status", required = true) Integer status,
			@RequestParam(value = "tecnico", required = false, defaultValue = "0") Integer tecnico,
			@RequestParam(value = "prioridad", required = false, defaultValue = "0") Integer prioridad,
			@RequestParam(value = "motivo", required = false, defaultValue = "0") Integer motivo,
			@RequestParam(value = "cliente", required = false, defaultValue = "0") Long cliente,
			@RequestParam(value = "fecha", required = false) @DateTimeFormat(iso = ISO.DATE) Date fecha) {

		EstadoOrden e = estadoOrdenRepo.findById(status).get();
		Tecnico t = tecnicoRepo.findById(tecnico).orElseGet(() -> null);
		Prioridad p = prioridadRepo.findById(prioridad).orElseGet(() -> null);
		MotivoOrden m = motivoOrdenRepo.findById(motivo).orElseGet(() -> null);
		Cliente c = clienteRepo.findById(cliente).orElseGet(() -> null);

		return ordenRepo.readByFilters(t, p, m, c, fecha, e);
	}

	@GetMapping("/atender")
	public List<Orden> consultarOrdenesAtender(
			@RequestParam(value = "status", required = false, defaultValue = "0") Integer status,
			@RequestParam(value = "tecnico", required = false, defaultValue = "0") Integer tecnico,
			@RequestParam(value = "prioridad", required = false, defaultValue = "0") Integer prioridad,
			@RequestParam(value = "motivo", required = false, defaultValue = "0") Integer motivo,
			@RequestParam(value = "cliente", required = false, defaultValue = "0") Long cliente,
			@RequestParam(value = "fecha", required = false) @DateTimeFormat(iso = ISO.DATE) Date fecha) {

		Collection<EstadoOrden> e = new ArrayList<>();
		if (status == 0) {
			// reservada, atendiendo, y pendientes
			e = estadoOrdenRepo.findByIdEstadoOrdenIn(Arrays.asList(1, 2, 3));
		} else {
			e = estadoOrdenRepo.findByIdEstadoOrdenIn(Arrays.asList(status));
		}

		Tecnico t = tecnicoRepo.findById(tecnico).orElseGet(() -> null);
		Prioridad p = prioridadRepo.findById(prioridad).orElseGet(() -> null);
		MotivoOrden m = motivoOrdenRepo.findById(motivo).orElseGet(() -> null);
		Cliente c = clienteRepo.findById(cliente).orElseGet(() -> null);

		return ordenRepo.readByFiltersAndEstados(t, p, m, c, fecha, e);
	}

	@GetMapping("/cobrar")
	public List<Orden> consultarOrdenesCobrar(
			@RequestParam(value = "orden", required = false, defaultValue = "0") Long orden,
			@RequestParam(value = "cliente", required = false, defaultValue = "0") Long cliente) {

		EstadoOrden e = estadoOrdenRepo.findById(4).get(); // finalizada

		Cliente c = clienteRepo.findById(cliente).orElseGet(() -> null);

		return ordenRepo.readByIdOrdenAndClienteAndEstado(orden, c, e);
	}

	@GetMapping("/cobradas")
	public List<Orden> consultarOrdenesCobradas(
			@RequestParam(value = "orden", required = false, defaultValue = "0") Long orden,
			@RequestParam(value = "cliente", required = false, defaultValue = "0") Long cliente,
			@RequestParam(value = "fecha", required = false) @DateTimeFormat(iso = ISO.DATE) Date fecha) {

		Collection<EstadoOrden> e = estadoOrdenRepo.findByIdEstadoOrdenIn(Arrays.asList(5, 6));// cobradas y entregadas,
																								// solo entregadas
		Cliente c = clienteRepo.findById(cliente).orElseGet(() -> null);
		return ordenRepo.readByIdOrdenAndClienteAndEstadoAndFecha(orden, c, e, fecha);
	}

	@PostMapping
	public Orden generarOrden(@RequestParam("imageFile") MultipartFile file,
			@RequestParam(value = "orden", required = true) String ord,
			@RequestParam(value = "idtecnico", required = true) Integer idTecnico,
			@RequestParam(value = "idprioridad", required = true) Integer idPrioridad,
			@RequestParam(value = "idmotivo", required = true) Integer idMotivo,
			@RequestParam(value = "idusuario", required = true) Integer idUsuario,
			@RequestParam(value = "idcliente", required = true) Long idCliente)
			throws JsonMappingException, JsonProcessingException, JsonParseException, IOException {

		Orden orden = new ObjectMapper().readValue(ord, Orden.class);

		// obtener entidades relacionadas o en su caso lanzar un mensaje de que no //
		// existe el recruso
		Usuario user = usuarioRepo.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException(idUsuario.toString()));
		Tecnico tec = tecnicoRepo.findById(idTecnico)
				.orElseThrow(() -> new RecursoNoEncontradoException(idTecnico.toString()));
		Prioridad prio = prioridadRepo.findById(idPrioridad)
				.orElseThrow(() -> new RecursoNoEncontradoException(idPrioridad.toString()));
		EstadoOrden est = estadoOrdenRepo.findById(1).get(); // estado id 1 reservada
		MotivoOrden mot = motivoOrdenRepo.findById(idMotivo)
				.orElseThrow(() -> new RecursoNoEncontradoException(idMotivo.toString()));
		Cliente cl = clienteRepo.findById(idCliente)
				.orElseThrow(() -> new RecursoNoEncontradoException(idCliente.toString()));

		orden.setImagenReferencia(file.getBytes());
		orden.setTecnico(tec);
		orden.setPrioridad(prio);
		orden.setEstadoOrden(est);
		orden.setMotivoOrden(mot);
		orden.setUsuario(user);
		orden.setCliente(cl);

		Orden o = ordenRepo.save(orden);
		log.info("Nueva orden reservada");
		return o;
	}

	@PutMapping("/{id}")
	public Orden actualizarOrden(@PathVariable Long id, @RequestParam("imageFile") MultipartFile file,
			@RequestParam(value = "orden", required = false) String ord,
			@RequestParam(value = "idtecnico", required = false) Integer idTecnico,
			@RequestParam(value = "idprioridad", required = false) Integer idPrioridad,
			@RequestParam(value = "idmotivo", required = false) Integer idMotivo,
			@RequestParam(value = "idcliente", required = false) Long idCliente)
			throws JsonMappingException, JsonProcessingException, JsonParseException, IOException {

		Orden orden = new ObjectMapper().readValue(ord, Orden.class);

		// obtener entidades relacionadas o en su caso lanzar un mensaje de que no //
		// existe el recruso
		Tecnico tec = tecnicoRepo.findById(idTecnico)
				.orElseThrow(() -> new RecursoNoEncontradoException(idTecnico.toString()));
		Prioridad prio = prioridadRepo.findById(idPrioridad)
				.orElseThrow(() -> new RecursoNoEncontradoException(idPrioridad.toString()));
		MotivoOrden mot = motivoOrdenRepo.findById(idMotivo)
				.orElseThrow(() -> new RecursoNoEncontradoException(idMotivo.toString()));
		Cliente cl = clienteRepo.findById(idCliente)
				.orElseThrow(() -> new RecursoNoEncontradoException(idCliente.toString()));

		orden.setIdOrden(id);
		orden.setImagenReferencia(file.getBytes());
		orden.setTecnico(tec);
		orden.setPrioridad(prio);
		orden.setMotivoOrden(mot);
		orden.setCliente(cl);

		Orden o = ordenRepo.save(orden);
		log.info("Orden actualizada");
		return o;
	}

	@PutMapping("/ponerpendiente/{id}")
	public Orden ponerOrdenPendiente(@PathVariable Long id, @RequestBody Orden ordenActualizar) {

		Orden o = ordenRepo.findById(id).map(orden -> {

			EstadoOrden est = estadoOrdenRepo.findById(3).get(); // estado id 3 pendiente
			orden.setEstadoOrden(est);
			orden.setDiagnosticoTecnico(ordenActualizar.getDiagnosticoTecnico());
			orden.setAplicable(ordenActualizar.getAplicable());
			orden.setDescripcionExtra(ordenActualizar.getDescripcionExtra());
			orden.setCostoExtra(ordenActualizar.getCostoExtra());
			return ordenRepo.save(orden);

		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		log.info("Orden puesta en pendiente");
		return o;
	}

	@PutMapping("/atender/{id}")
	public Orden atenderOrden(@PathVariable Long id) {

		Orden o = ordenRepo.findById(id).map(orden -> {

			EstadoOrden est = estadoOrdenRepo.findById(2).get(); // estado id 2 atendiendo
			orden.setEstadoOrden(est);
			return ordenRepo.save(orden);

		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		log.info("Orden atendiendo");
		return o;
	}

	@PutMapping("/finalizar/{id}")
	public Orden finalizarOrden(@PathVariable Long id, @RequestBody Orden ordenActualizar) {

		Orden o = ordenRepo.findById(id).map(orden -> {

			EstadoOrden est = estadoOrdenRepo.findById(4).get(); // estado id 4 finalizada
			orden.setEstadoOrden(est);
			orden.setDiagnosticoTecnico(ordenActualizar.getDiagnosticoTecnico());
			orden.setAplicable(ordenActualizar.getAplicable());
			orden.setTotalCostoServicio(ordenActualizar.getTotalCostoServicio());
			orden.setCostoServicios(ordenActualizar.getCostoServicios());
			orden.setCostoRepuestos(ordenActualizar.getCostoRepuestos());
			orden.setDescripcionExtra(ordenActualizar.getDescripcionExtra());
			orden.setCostoExtra(ordenActualizar.getCostoExtra());
			return ordenRepo.save(orden);

		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		log.info("Orden puesta en pendiente");
		return o;
	}

	@PutMapping("/cobrar/{id}")
	public Orden cobrarOrden(@PathVariable Long id, @RequestBody Orden ordenActualizar) {

		Orden o = ordenRepo.findById(id).map(orden -> {

			EstadoOrden est = estadoOrdenRepo.findById(5).get(); // estado id 5 cobrada y entregada
			orden.setEstadoOrden(est);
			orden.setFechaEntrega(ordenActualizar.getFechaEntrega());

			return ordenRepo.save(orden);

		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		log.info("Orden cobrada	");
		return o;
	}

	@PutMapping("/entregar/{id}")
	public Orden entregarOrden(@PathVariable Long id, @RequestBody Orden ordenActualizar) {

		Orden o = ordenRepo.findById(id).map(orden -> {

			EstadoOrden est = estadoOrdenRepo.findById(6).get(); // estado id 6 solo entregada
			orden.setEstadoOrden(est);
			orden.setFechaEntrega(ordenActualizar.getFechaEntrega());

			return ordenRepo.save(orden);

		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		log.info("Orden cobrada	");
		return o;
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {

		Orden orden = ordenRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

		EstadoOrden est = estadoOrdenRepo.findById(7).get(); // estado id 7 eliminada

		orden.setEstadoOrden(est);

		ordenRepo.save(orden);
		log.info("Orden eliminada");
	}

	@GetMapping("/cliente/{id}")
	public List<Orden> consultarOrdenesCliente(@PathVariable Long id) {

		Cliente c = clienteRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

		// finalizada, cobrada y entregada, reservada
		Collection<EstadoOrden> e = estadoOrdenRepo.findByIdEstadoOrdenIn(Arrays.asList(4, 5, 1));

		return ordenRepo.findByClienteAndEstadoOrdenIn(c, e);
	}

	@GetMapping("/{idOrden}")
	public Orden infoOrdenServicio(@PathVariable Long idOrden) {

		Orden orden = ordenRepo.findById(idOrden)
				.orElseThrow(() -> new RecursoNoEncontradoException(idOrden.toString()));
		return orden;

	}

	@PostMapping("/{idorden}/addrepuesto")
	public RepuestoEmpleado addRepuestoOrden(@RequestBody RepuestoEmpleado r, @PathVariable Long idorden) {

		Orden o = ordenRepo.findById(idorden).orElseThrow(() -> new RecursoNoEncontradoException(idorden.toString()));

		r.setOrden(o);

		int cantidad = r.getCantidad();
		InventarioRepuesto repuesto = r.getInventarioRepuesto();
		repuesto.setExistencia(repuesto.getExistencia() - cantidad);

		inventarioRepo.save(repuesto);

		return repuestoEmpleadoRepo.save(r);
	}

	@GetMapping("/{idOrden}/repuestosempleados")
	public List<RepuestoEmpleado> listarRepuestosEmpleados(@PathVariable Long idOrden) {

		Orden orden = ordenRepo.findById(idOrden)
				.orElseThrow(() -> new RecursoNoEncontradoException(idOrden.toString()));

		return repuestoEmpleadoRepo.findByOrden(orden);

	}

	@DeleteMapping("/eliminarrepuestoempleado/{id}")
	public void eliminarRepuestoEmpleado(@PathVariable Long id) {

		RepuestoEmpleado rp = repuestoEmpleadoRepo.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

		int cantidad = rp.getCantidad();
		InventarioRepuesto repuesto = rp.getInventarioRepuesto();
		repuesto.setExistencia(repuesto.getExistencia() + cantidad);

		inventarioRepo.save(repuesto);

		repuestoEmpleadoRepo.deleteById(id);
	}

	@PostMapping("/{idorden}/addservicio")
	public ServicioAplicado addServicioAplicado(@RequestBody ServicioAplicado s, @PathVariable Long idorden) {

		Orden o = ordenRepo.findById(idorden).orElseThrow(() -> new RecursoNoEncontradoException(idorden.toString()));

		s.setOrden(o);

		return servicioAplicadoRepo.save(s);
	}

	@GetMapping("/{idOrden}/serviciosaplicados")
	public List<ServicioAplicado> listarServiciosAplicados(@PathVariable Long idOrden) {

		Orden orden = ordenRepo.findById(idOrden)
				.orElseThrow(() -> new RecursoNoEncontradoException(idOrden.toString()));

		return servicioAplicadoRepo.findByOrden(orden);

	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {

		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {

			int count = deflater.deflate(buffer);

			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();

		} catch (IOException e) {
		}

		return outputStream.toByteArray();

	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		try {

			while (!inflater.finished()) {

				int count = inflater.inflate(buffer);

				outputStream.write(buffer, 0, count);

			}

			outputStream.close();

		} catch (IOException ioe) {

		} catch (DataFormatException e) {

		}

		return outputStream.toByteArray();
	}

	@DeleteMapping("/eliminarservicioaplicado/{id}")
	public void eliminarServicioAplicado(@PathVariable Long id) {
		servicioAplicadoRepo.deleteById(id);
	}

	@PostMapping("/{idorden}/addfoto")
	public FotosDiagnostico addFotosDiagnostico(@PathVariable Long idorden,
			@RequestParam("imageFile") MultipartFile file,
			@RequestParam(value = "fotodiagnostico", required = true) String fotodiagnostico)
			throws JsonMappingException, JsonProcessingException, JsonParseException, IOException {

		Orden o = ordenRepo.findById(idorden).orElseThrow(() -> new RecursoNoEncontradoException(idorden.toString()));

		FotosDiagnostico foto = new ObjectMapper().readValue(fotodiagnostico, FotosDiagnostico.class);

		foto.setImagen(file.getBytes());
		foto.setEstado(1);
		foto.setOrden(o);

		return fotosDiagnosticoRepo.save(foto);

	}

	@GetMapping("/{idOrden}/fotosdiagnostico")
	public List<FotosDiagnostico> listarFotosDiagnostico(@PathVariable Long idOrden) {

		Orden orden = ordenRepo.findById(idOrden)
				.orElseThrow(() -> new RecursoNoEncontradoException(idOrden.toString()));

		return fotosDiagnosticoRepo.findByOrden(orden);

	}

	@DeleteMapping("/eliminarfotodiagnostico/{id}")
	public void eliminarFotoDiagnostico(@PathVariable Long id) {
		fotosDiagnosticoRepo.deleteById(id);
	}
}
