package com.bakend.CB_BE_RayDelCarmen.exception;

public class InvalidAmountException extends RuntimeException {

	public InvalidAmountException() {
		super("Amount must be greater than zero");
	}
}
