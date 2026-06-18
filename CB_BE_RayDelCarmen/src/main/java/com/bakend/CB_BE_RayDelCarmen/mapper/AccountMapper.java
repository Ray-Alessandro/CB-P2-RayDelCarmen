package com.bakend.CB_BE_RayDelCarmen.mapper;

import com.bakend.CB_BE_RayDelCarmen.dto.AccountResponse;
import com.bakend.CB_BE_RayDelCarmen.model.Account;

public interface AccountMapper {

	AccountResponse toResponse(Account account);
}
