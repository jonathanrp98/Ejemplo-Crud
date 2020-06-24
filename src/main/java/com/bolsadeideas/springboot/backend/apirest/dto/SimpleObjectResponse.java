package com.bolsadeideas.springboot.backend.apirest.dto;



public class SimpleObjectResponse {

	
	private Integer codigo;

	private String mensaje;

	private Object valor;

	public SimpleObjectResponse() {
		super();
	}

	public SimpleObjectResponse(Integer codigo, String mensaje, Object valor) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.valor = valor;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

}
