package com.bakend.CB_BE_RayDelCarmen.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bakend.CB_BE_RayDelCarmen.model.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

	private Long id;
	private TransactionType type;
	private BigDecimal amount;
	private LocalDateTime createdAt;
	private Long accountId;
}
