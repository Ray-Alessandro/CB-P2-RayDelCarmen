package com.bakend.CB_BE_RayDelCarmen.mapper.impl;

import org.springframework.stereotype.Component;

import com.bakend.CB_BE_RayDelCarmen.dto.AccountResponse;
import com.bakend.CB_BE_RayDelCarmen.mapper.AccountMapper;
import com.bakend.CB_BE_RayDelCarmen.model.Account;

@Component
public class AccountMapperImpl implements AccountMapper {

	@Override
	public AccountResponse toResponse(Account account) {
		return AccountResponse.builder()
				.id(account.getId())
				.accountNumber(account.getAccountNumber())
				.balance(account.getBalance())
				.build();
	}
}
