package com.bakend.CB_BE_RayDelCarmen.exception;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(Long id) {
		super("Account not found with id: " + id);
	}
}
