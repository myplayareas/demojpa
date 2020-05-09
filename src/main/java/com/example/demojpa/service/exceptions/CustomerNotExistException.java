package com.example.demojpa.service.exceptions;

public class CustomerNotExistException extends Exception {
	private static final long serialVersionUID = 1218838284374009907L;

	public CustomerNotExistException(String message) {
		super(message);
	}
}
