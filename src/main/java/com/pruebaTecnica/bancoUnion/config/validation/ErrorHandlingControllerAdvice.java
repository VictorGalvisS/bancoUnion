package com.pruebaTecnica.bancoUnion.config.validation;

import com.pruebaTecnica.bancoUnion.config.validation.dto.ValidationErrorDto;
import com.pruebaTecnica.bancoUnion.config.validation.dto.ValidationErrorResponseDto;
import com.pruebaTecnica.bancoUnion.config.validation.excepcion.ValidationDataException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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

import java.util.Properties;
import java.util.UUID;

import static com.pruebaTecnica.bancoUnion.config.validation.dto.ValidationErrorDto.newInstanceValidationErrorDto;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    protected static final Logger log = LoggerFactory.getLogger(ErrorHandlingControllerAdvice.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onConstraintValidationException(ConstraintViolationException e) {
        String idFailure = UUID.randomUUID().toString();
        Properties properties = new Properties();
        properties.setProperty("idRequest", idFailure);
        System.setProperties(properties);

        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        for (@SuppressWarnings("rawtypes")
        ConstraintViolation violation : e.getConstraintViolations()) {
            ValidationErrorDto dto = newInstanceValidationErrorDto(idFailure, violation.getPropertyPath().toString(),
                    violation.getMessage(), violation.getInvalidValue().toString());
            error.getListValidationErrorDto().add(dto);
            log.error(violation.getMessage(), violation);
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String idFailure = UUID.randomUUID().toString();
        Properties properties = new Properties();
        properties.setProperty("idRequest", idFailure);
        System.setProperties(properties);

        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            ValidationErrorDto dto = newInstanceValidationErrorDto(idFailure,fieldError.getField(),
                    fieldError.getDefaultMessage(), fieldError.getRejectedValue().toString());
            error.getListValidationErrorDto().add(dto);
            log.error(fieldError.getDefaultMessage(), fieldError);
        }
        return error;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String idFailure = UUID.randomUUID().toString();
        Properties properties = new Properties();
        properties.setProperty("idRequest", idFailure);
        System.setProperties(properties);

        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        ValidationErrorDto dto = newInstanceValidationErrorDto(idFailure,e.getParameterName(), e.getMessage(), null);
        error.getListValidationErrorDto().add(dto);
        log.error(e.getMessage(), e);
        return error;
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onMissingRequestHeaderException(MissingRequestHeaderException e) {
        String idFailure = UUID.randomUUID().toString();
        Properties properties = new Properties();
        properties.setProperty("idRequest", idFailure);
        System.setProperties(properties);

        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        ValidationErrorDto dto = newInstanceValidationErrorDto(idFailure,e.getHeaderName(), e.getMessage(), null);
        error.getListValidationErrorDto().add(dto);
        log.error(e.getMessage(), e);
        return error;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String idFailure = UUID.randomUUID().toString();
        Properties properties = new Properties();
        properties.setProperty("idRequest", idFailure);
        System.setProperties(properties);

        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        ValidationErrorDto dto = newInstanceValidationErrorDto(idFailure,e.getName(), e.getMessage(), e.getValue().toString());
        error.getListValidationErrorDto().add(dto);
        log.error(e.getMessage(), e);
        return error;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onNullPointerException(NullPointerException e) {
        String idFailure = UUID.randomUUID().toString();
        Properties properties = new Properties();
        properties.setProperty("idRequest", idFailure);
        System.setProperties(properties);

        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        ValidationErrorDto dto = newInstanceValidationErrorDto(idFailure,null, e.getMessage(), null);
        error.getListValidationErrorDto().add(dto);
        log.error(e.getMessage(), e);
        return error;
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onUnexpectedTypeException(UnexpectedTypeException e) {
        String idFailure = UUID.randomUUID().toString();
        Properties properties = new Properties();
        properties.setProperty("idRequest", idFailure);
        System.setProperties(properties);

        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        ValidationErrorDto dto = newInstanceValidationErrorDto(idFailure,null, e.getMessage(), null);
        error.getListValidationErrorDto().add(dto);
        log.error(e.getMessage(), e);
        return error;
    }

    @ExceptionHandler(ValidationDataException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto onDateExceptionError(ValidationDataException e) {
        String idFailure = UUID.randomUUID().toString();
        Properties properties = new Properties();
        properties.setProperty("idRequest", idFailure);
        System.setProperties(properties);

        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        ValidationErrorDto dto = newInstanceValidationErrorDto(idFailure,null, e.getMessage(), null);
        error.getListValidationErrorDto().add(dto);
        log.error(e.getMessage(), e);
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidationErrorResponseDto exceptionHandler(Exception e) {
        String idFailure = UUID.randomUUID().toString();
        Properties properties = new Properties();
        properties.setProperty("idRequest", idFailure);
        System.setProperties(properties);

        ValidationErrorResponseDto error = new ValidationErrorResponseDto();
        ValidationErrorDto dto = newInstanceValidationErrorDto(idFailure,null, e.getMessage(), null);
        error.getListValidationErrorDto().add(dto);
        log.error(e.getMessage(), e);
        return error;
    }

}
