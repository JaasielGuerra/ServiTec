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
@RequestMapping("/clientes")
public class RestClientesController {

	private static final Logger log = LoggerFactory.getLogger(RestClientesController.class);

	@Autowired
	private IUsuarioRepo usuarioRepo;
	@Autowired
	private IClienteRepo clienteRepo;

	@PostMapping
	public ResponseEntity<Cliente> crear(@RequestBody Cliente c,
			@RequestParam(value = "idusuario", required = true) Integer idUser) {

		Cliente clienteTest = clienteRepo.findByCodigoCliente(c.getCodigoCliente()).orElseGet(() -> null);

		if (clienteTest != null) {
			return new ResponseEntity(new Mensaje("El código ya existe.", 1), HttpStatus.OK);
		}

		// obtener entidades relacionadas o en su caso lanzar un mensaje de que no
		// existe el recruso
		Usuario u = usuarioRepo.findById(idUser).orElseThrow(() -> new RecursoNoEncontradoException(idUser.toString()));

		c.setUsuario(u);
		c.setEstado(1);
		c.setFechaCommit(Date.valueOf(LocalDate.now()));
		c.setHoraCommit(Time.valueOf(LocalTime.now()));

		log.info("Nuevo cliente creado");

		return new ResponseEntity<Cliente>(clienteRepo.save(c), HttpStatus.CREATED);
	}

	@GetMapping
	public List<Cliente> consultar(@RequestParam(value = "status", defaultValue = "1", required = false) Integer status,
			@RequestParam(value = "buscar", defaultValue = "", required = false) String buscar) {

		log.info("buscando: " + buscar);
		List<Cliente> l = clienteRepo.findByEstadoAndNombreClienteContaining(status, buscar);

		return l;
	}

	@GetMapping("/{id}")
	public Cliente obtenerPorId(@PathVariable Long id) {
		Cliente l = clienteRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
		return l;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizar(@RequestBody Cliente c,
			@RequestParam(value = "idusuario", required = true) Integer idUser, @PathVariable Long id) {

		Cliente clienteTest = clienteRepo.findByCodigoClienteAndIdClienteNot(c.getCodigoCliente(), id)
				.orElseGet(() -> null);

		if (clienteTest != null) {
			return new ResponseEntity(new Mensaje("El código ya existe.", 1), HttpStatus.OK);
		}

		// obtener entidades relacionadas o en su caso lanzar un mensaje de que no
		// existe el recruso

		Cliente client = clienteRepo.findById(id).map(cliente -> {

			cliente.setCodigoCliente(c.getCodigoCliente());
			cliente.setNombreCliente(c.getNombreCliente());
			cliente.setTelefono(c.getTelefono());
			cliente.setReferencia(c.getReferencia());

			return clienteRepo.save(cliente);

		}).orElseGet(() -> { // sino existe el recurso a actualizar, entonces se crea uno

			Usuario u = usuarioRepo.findById(idUser)
					.orElseThrow(() -> new RecursoNoEncontradoException(idUser.toString()));

			c.setIdCliente(null);
			c.setUsuario(u);
			c.setEstado(1);
			c.setFechaCommit(Date.valueOf(LocalDate.now()));
			c.setHoraCommit(Time.valueOf(LocalTime.now()));

			return clienteRepo.save(c);
		});

		log.info("cliente actualizado con exito");

		return new ResponseEntity<Cliente>(client, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {

		clienteRepo.findById(id).map(c -> { // se cambia a estado 0 eliminado
			c.setEstado(0);
			return clienteRepo.save(c);
		}).orElseThrow(() -> new RecursoNoEncontradoException(id.toString()));
	}

	@GetMapping("/obtenerporcodigo/{codigo}")
	private ResponseEntity<Cliente> obtenerPorCodigo(@PathVariable String codigo) {
		Cliente cliente = clienteRepo.findByCodigoCliente(codigo).orElseGet(() -> null);

		if (cliente == null) {
			return new ResponseEntity(new Mensaje("El código es incorrecto, no se encontró ningún resultado.", 1),
					HttpStatus.OK);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

}
