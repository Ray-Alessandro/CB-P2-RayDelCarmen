package com.bakend.CB_BE_RayDelCarmen.dto;

import java.math.BigDecimal;

import com.bakend.CB_BE_RayDelCarmen.model.TransactionType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

	@NotNull
	private Long accountId;

	@NotNull
	private TransactionType type;

	@NotNull
	@Positive
	private BigDecimal amount;
}
