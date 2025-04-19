package com.product.common.dto;

/**
 * Clase que manda los mensajes http
 * */
public class ApiResponse {
		
	private String message;
	
	/*
	 * Constructor de clase
	 * @param mensaje, mensaje que se que imprime
	 * */
	public ApiResponse(String message) {
		super();
		this.message = message;
	}
	
	/*
	 * devuelve el mensaje
	 * @param
	 * */
	public String getMessage() {
		return message;
	}
	
	/*
	 * asigna el mensaje
	 * @param mensaje, el mensaje que se asirgnara
	 * */
	public void setMessage(String message) {
		this.message = message;
	}

}
