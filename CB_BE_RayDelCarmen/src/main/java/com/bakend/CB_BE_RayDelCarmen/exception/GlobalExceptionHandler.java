package com.bakend.CB_BE_RayDelCarmen.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bakend.CB_BE_RayDelCarmen.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFound(AccountNotFoundException exception) {
		return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler({ InsufficientBalanceException.class, InvalidAmountException.class })
	public ResponseEntity<ErrorResponse> handleBusinessRuleViolation(RuntimeException exception) {
		return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException exception) {
		String message = exception.getBindingResult().getFieldErrors().stream()
				.findFirst()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.orElse("Invalid request");

		return buildErrorResponse(HttpStatus.BAD_REQUEST, message);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleUnreadableMessage(HttpMessageNotReadableException exception) {
		return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid request body");
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(status.value())
				.message(message)
				.timestamp(LocalDateTime.now())
				.build();

		return ResponseEntity.status(status).body(errorResponse);
	}
}
