package com.izan.backend.mvc.exceptions;

public class UsuarioNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioNotFoundException() {
		super();
	}
	
	public UsuarioNotFoundException(String message) {
		super(message);
	}
	
	public UsuarioNotFoundException(long id) {
		super("Usuario" + id + " no encontrado. ");
	}
}
