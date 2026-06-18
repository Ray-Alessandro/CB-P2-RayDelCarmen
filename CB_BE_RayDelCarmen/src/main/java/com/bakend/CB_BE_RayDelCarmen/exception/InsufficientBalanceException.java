package com.bakend.CB_BE_RayDelCarmen.exception;

public class InsufficientBalanceException extends RuntimeException {

	public InsufficientBalanceException(Long accountId) {
		super("Insufficient balance for account id: " + accountId);
	}
}
