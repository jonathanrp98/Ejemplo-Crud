package com.bolsadeideas.springboot.backend.apirest.dto;

import com.bolsadeideas.springboot.backend.apirest.dto.util.LocationType;
import com.bolsadeideas.springboot.backend.apirest.dto.util.ResponseType;

public class SimpleObjectMessage {

	
	private String codigo;

	private ResponseType tipo;
	
	private LocationType descripcion;

	private String ubicacion;
	
	public SimpleObjectMessage(String codigo, ResponseType tipo, LocationType descripcion, String ubicacion) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.ubicacion = ubicacion;
	}

	public SimpleObjectMessage() {
		super();
	}

	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public ResponseType getTipo() {
		return tipo;
	}

	public void setTipo(ResponseType tipo) {
		this.tipo = tipo;
	}

	public LocationType getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(LocationType descripcion) {
		this.descripcion = descripcion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
