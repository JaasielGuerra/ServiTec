package com.umg.servitecweb.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.umg.servitecweb.model.Mensaje;
import com.umg.servitecweb.model.MotivoOrden;
import com.umg.servitecweb.model.Orden;
import com.umg.servitecweb.model.Prioridad;
import com.umg.servitecweb.model.Tecnico;
import com.umg.servitecweb.model.Usuario;
import com.umg.servitecweb.repository.IClienteRepo;
import com.umg.servitecweb.repository.IEstadoOrdenRepo;
import com.umg.servitecweb.repository.IMotivoOrdenRepo;
import com.umg.servitecweb.repository.IOrdenRepo;
import com.umg.servitecweb.repository.IPrioridadRepo;
import com.umg.servitecweb.repository.ITecnicoRepo;
import com.umg.servitecweb.repository.IUsuarioRepo;

@CrossOrigin(origins = "http://localhost:4200")
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

	@PostMapping
	public Orden generarOrden(@RequestParam("imageFile") MultipartFile file,
			@RequestParam(value = "orden", required = false) String ord,
			@RequestParam(value = "idtecnico", required = false) Integer idTecnico,
			@RequestParam(value = "idprioridad", required = false) Integer idPrioridad,
			@RequestParam(value = "idmotivo", required = false) Integer idMotivo,
			@RequestParam(value = "idusuario", required = false) Integer idUsuario,
			@RequestParam(value = "idcliente", required = false) Long idCliente)
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

		log.info("Orden - " + orden);
		log.info("Tamanio original de la imagen - " + file.getBytes().length);
		log.info("Tamanio comprimido de la imagen - " + compressBytes(file.getBytes()));

		orden.setImagenReferencia(compressBytes(file.getBytes()));
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

	@GetMapping("/cliente/{id}")
	public List<Orden> consultarOrdenesCliente(@PathVariable Long id) {

		Cliente c = clienteRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));

		// finalizada, cobrada y entregada, reservada
		Collection<EstadoOrden> e = estadoOrdenRepo.findByIdEstadoOrdenIn(Arrays.asList(4, 5, 1));

		return ordenRepo.findByClienteAndEstadoOrdenIn(c, e);
	}

	@GetMapping("/{idOrden}")
	public Orden infoOrdenServicio(@PathVariable Long idOrden) {

		return ordenRepo.findById(idOrden).orElseThrow(() -> new RecursoNoEncontradoException(idOrden.toString()));

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

}
