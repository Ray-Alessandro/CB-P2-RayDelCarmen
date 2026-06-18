package com.bakend.CB_BE_RayDelCarmen.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakend.CB_BE_RayDelCarmen.dto.TransactionRequest;
import com.bakend.CB_BE_RayDelCarmen.dto.TransactionResponse;
import com.bakend.CB_BE_RayDelCarmen.mapper.TransactionMapper;
import com.bakend.CB_BE_RayDelCarmen.model.Transaction;
import com.bakend.CB_BE_RayDelCarmen.service.TransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;
	private final TransactionMapper transactionMapper;

	@PostMapping
	public ResponseEntity<TransactionResponse> registerTransaction(@Valid @RequestBody TransactionRequest request) {
		Transaction transaction = transactionService.registerTransaction(
				request.getAccountId(),
				request.getType(),
				request.getAmount());

		return ResponseEntity.status(HttpStatus.CREATED).body(transactionMapper.toResponse(transaction));
	}
}
