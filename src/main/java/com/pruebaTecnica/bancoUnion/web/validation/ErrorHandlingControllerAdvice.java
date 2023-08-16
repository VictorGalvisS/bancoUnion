package com.pruebaTecnica.bancoUnion.web.validation;

import com.pruebaTecnica.bancoUnion.web.validation.excepcion.ValidationDataException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.pruebaTecnica.bancoUnion.web.validation.ValidationErrorDto.newInstanceValidationErrorDto;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onConstraintValidationException(ConstraintViolationException e) {
        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        for (@SuppressWarnings("rawtypes")
        ConstraintViolation violation : e.getConstraintViolations()) {
            error.getListValidationErrorDto().add(
                    newInstanceValidationErrorDto(violation.getPropertyPath().toString(),
                            violation.getMessage(), violation.getInvalidValue().toString()));
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getListValidationErrorDto().add(
                    newInstanceValidationErrorDto(fieldError.getField(),
                            fieldError.getDefaultMessage(), fieldError.getRejectedValue().toString()));
        }
        return error;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        error.getListValidationErrorDto().add(
                newInstanceValidationErrorDto(e.getParameterName(), e.getMessage(), null));
        return error;
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onMissingRequestHeaderException(MissingRequestHeaderException e) {
        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        error.getListValidationErrorDto().add(
                newInstanceValidationErrorDto(e.getHeaderName(), e.getMessage(), null));
        return error;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        error.getListValidationErrorDto()
                .add(newInstanceValidationErrorDto(e.getName(), e.getMessage(), e.getValue().toString()));
        return error;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onNullPointerException(NullPointerException e) {
        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        error.getListValidationErrorDto().add(
                newInstanceValidationErrorDto(null, e.getMessage(), null));
        return error;
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onUnexpectedTypeException(UnexpectedTypeException e) {
        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        error.getListValidationErrorDto().add(
                newInstanceValidationErrorDto(null, e.getMessage(), null));
        return error;
    }

    @ExceptionHandler(ValidationDataException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onDateExceptionError(ValidationDataException e) {
        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        error.getListValidationErrorDto().add(
                newInstanceValidationErrorDto(null, e.getMessage(), null));
        return error;
    }

}
