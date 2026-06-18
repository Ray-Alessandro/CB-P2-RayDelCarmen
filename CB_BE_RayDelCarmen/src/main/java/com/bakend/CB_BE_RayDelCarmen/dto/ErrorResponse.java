package com.bakend.CB_BE_RayDelCarmen.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {

	private int status;
	private String message;
	private LocalDateTime timestamp;
}
