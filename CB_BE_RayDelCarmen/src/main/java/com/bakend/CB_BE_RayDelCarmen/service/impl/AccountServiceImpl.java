package com.bakend.CB_BE_RayDelCarmen.service.impl;

import org.springframework.stereotype.Service;

import com.bakend.CB_BE_RayDelCarmen.exception.AccountNotFoundException;
import com.bakend.CB_BE_RayDelCarmen.model.Account;
import com.bakend.CB_BE_RayDelCarmen.repository.AccountRepository;
import com.bakend.CB_BE_RayDelCarmen.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public Account getAccountById(Long id) {
		return accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));
	}
}
