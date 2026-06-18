package com.bakend.CB_BE_RayDelCarmen.mapper.impl;

import org.springframework.stereotype.Component;

import com.bakend.CB_BE_RayDelCarmen.dto.TransactionResponse;
import com.bakend.CB_BE_RayDelCarmen.mapper.TransactionMapper;
import com.bakend.CB_BE_RayDelCarmen.model.Transaction;

@Component
public class TransactionMapperImpl implements TransactionMapper {

	@Override
	public TransactionResponse toResponse(Transaction transaction) {
		return TransactionResponse.builder()
				.id(transaction.getId())
				.type(transaction.getType())
				.amount(transaction.getAmount())
				.createdAt(transaction.getCreatedAt())
				.accountId(transaction.getAccount().getId())
				.build();
	}
}
