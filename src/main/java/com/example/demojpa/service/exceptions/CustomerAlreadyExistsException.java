package com.example.demojpa.service.exceptions;

public class CustomerAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1783995177863638667L;

	public CustomerAlreadyExistsException(String message) {
		super(message);
	}
	
}
