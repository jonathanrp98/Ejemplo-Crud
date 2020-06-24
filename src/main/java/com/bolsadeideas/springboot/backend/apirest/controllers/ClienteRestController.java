package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.dto.SimpleObjectMessage;
import com.bolsadeideas.springboot.backend.apirest.dto.SimpleObjectResponse;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.services.IClienteService;
import com.bolsadeideas.springboot.backend.apirest.security.exceptions.BadRequestException;
import com.bolsadeideas.springboot.backend.apirest.security.exceptions.NotFoundException;
import com.bolsadeideas.springboot.backend.apirest.dto.util.LocationType;
import com.bolsadeideas.springboot.backend.apirest.dto.util.ResponseType;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT})
public class ClienteRestController {
	
	public static final String URL_CONTROLLER = "/api";

	@Autowired
	private IClienteService clienteService;

	
	@GetMapping("/clientes")
	public ResponseEntity<SimpleObjectResponse> index() {
		return new ResponseEntity<>(new SimpleObjectResponse( HttpStatus.OK.value(), "lista de estudiantes obtenidas con éxito",
						clienteService.findAll()), HttpStatus.OK);
	}
	

	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Long id) {
		return this.clienteService.findById(id);
	}

	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SimpleObjectResponse> create(@RequestBody Cliente cliente) {
		
		try {
			cliente.setCreateAt(new Date());
			this.clienteService.save(cliente);
			return new ResponseEntity<>(
					new SimpleObjectResponse(HttpStatus.OK.value(), "creada con éxito", null), HttpStatus.OK);
		} catch (BadRequestException e) {
			return new ResponseEntity<>(new SimpleObjectResponse(HttpStatus.BAD_REQUEST.value(),
					"Error al insertar en la base de datos",
					new SimpleObjectMessage("ER-0002",ResponseType.ERROR, LocationType.OPERACION, URL_CONTROLLER)),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SimpleObjectResponse> update(@RequestBody Cliente cliente, @PathVariable Long id) {
		try {
			Cliente currentCliente = this.clienteService.findById(id);
			currentCliente.setNombre(cliente.getNombre());
			currentCliente.setApellido(cliente.getApellido());
			currentCliente.setEmail(cliente.getEmail());
			this.clienteService.save(currentCliente);
			// return currentCliente;
			return new ResponseEntity<>(
					new SimpleObjectResponse(HttpStatus.OK.value(), "actualizada con éxito", null), HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(new SimpleObjectResponse(HttpStatus.NOT_FOUND.value(), "El registro no existe",
					new SimpleObjectMessage("ER-0003", ResponseType.INFO, LocationType.FACHADA, URL_CONTROLLER)),
					HttpStatus.NOT_FOUND);
		} catch (BadRequestException e) {
			return new ResponseEntity<>(new SimpleObjectResponse(HttpStatus.BAD_REQUEST.value(),
					"Error al actualizar en la base de datos",
					new SimpleObjectMessage("ER-0002", ResponseType.ERROR, LocationType.OPERACION, URL_CONTROLLER)),
					HttpStatus.BAD_REQUEST);
		}
	}
		

	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<SimpleObjectResponse> delete(@PathVariable Long id) {
		Cliente currentCliente = this.clienteService.findById(id);
		this.clienteService.delete(currentCliente);
		return new ResponseEntity<>(
				new SimpleObjectResponse(HttpStatus.OK.value(), "eliminada con éxito", null), HttpStatus.OK);
	}
	
	
}