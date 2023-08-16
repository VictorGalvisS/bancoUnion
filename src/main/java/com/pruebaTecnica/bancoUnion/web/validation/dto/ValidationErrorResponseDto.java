package com.pruebaTecnica.bancoUnion.web.validation.dto;

import com.pruebaTecnica.bancoUnion.web.validation.dto.ValidationErrorDto;

import java.util.ArrayList;
import java.util.List;

public final class ValidationErrorResponseDto {
	private List<ValidationErrorDto> listValidationErrorDto = new ArrayList<>();

	public List<ValidationErrorDto> getListValidationErrorDto() {
		return listValidationErrorDto;
	}

	public void setListValidationErrorDto(List<ValidationErrorDto> listValidationErrorDto) {
		this.listValidationErrorDto = listValidationErrorDto;
	}
}
