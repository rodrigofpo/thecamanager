package com.demo.thecamanager.config.excepitionHandler;

import com.demo.thecamanager.config.excepitionHandler.excepition.BusinessException;
import com.demo.thecamanager.config.excepitionHandler.excepition.ConflictException;
import com.demo.thecamanager.config.excepitionHandler.excepition.UnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(BusinessException ex, HttpServletRequest request) {
        return response(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handlerUnprocessableEntity(UnprocessableEntityException ex, HttpServletRequest request) {
        return response(ex.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY, LocalDateTime.now());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handlerConflictException(ConflictException ex, HttpServletRequest request) {
        return response(ex.getMessage(), request, HttpStatus.CONFLICT, LocalDateTime.now());
    }

    private ResponseEntity<ErrorResponse> response(final String message, final HttpServletRequest request, final HttpStatus status, LocalDateTime data) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(message, data, status.value(), request.getRequestURI()));
    }
}
