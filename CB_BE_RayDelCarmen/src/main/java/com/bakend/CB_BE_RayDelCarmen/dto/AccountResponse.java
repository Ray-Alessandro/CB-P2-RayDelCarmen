package com.bakend.CB_BE_RayDelCarmen.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

	private Long id;
	private String accountNumber;
	private BigDecimal balance;
}
