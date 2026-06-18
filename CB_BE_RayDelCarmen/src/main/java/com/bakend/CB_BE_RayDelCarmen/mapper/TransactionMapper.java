package com.bakend.CB_BE_RayDelCarmen.mapper;

import com.bakend.CB_BE_RayDelCarmen.dto.TransactionResponse;
import com.bakend.CB_BE_RayDelCarmen.model.Transaction;

public interface TransactionMapper {

	TransactionResponse toResponse(Transaction transaction);
}
