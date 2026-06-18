package com.bakend.CB_BE_RayDelCarmen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakend.CB_BE_RayDelCarmen.dto.AccountResponse;
import com.bakend.CB_BE_RayDelCarmen.mapper.AccountMapper;
import com.bakend.CB_BE_RayDelCarmen.model.Account;
import com.bakend.CB_BE_RayDelCarmen.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;
	private final AccountMapper accountMapper;

	@GetMapping("/{id}")
	public ResponseEntity<AccountResponse> getAccount(@PathVariable Long id) {
		Account account = accountService.getAccountById(id);
		return ResponseEntity.ok(accountMapper.toResponse(account));
	}
}
