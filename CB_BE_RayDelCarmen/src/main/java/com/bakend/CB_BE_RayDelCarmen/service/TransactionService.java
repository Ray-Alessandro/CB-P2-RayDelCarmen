package com.bakend.CB_BE_RayDelCarmen.service;

import java.math.BigDecimal;

import com.bakend.CB_BE_RayDelCarmen.model.Transaction;
import com.bakend.CB_BE_RayDelCarmen.model.TransactionType;

public interface TransactionService {

	Transaction registerTransaction(Long accountId, TransactionType type, BigDecimal amount);
}
