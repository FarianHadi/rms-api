package com.farian.rms.RMSAPI.exception;

public class EntityNotFoundException extends RuntimeException{
	private String id;
	
	public EntityNotFoundException(String id, String message) {
		super(message);
		this.id = id;
	}
}
