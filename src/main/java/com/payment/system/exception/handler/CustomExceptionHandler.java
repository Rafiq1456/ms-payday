package com.payment.system.exception.handler;

import com.payment.system.exception.AlreadyExistException;
import com.payment.system.exception.AuthenticationException;
import com.payment.system.exception.NotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.payment.system.dto.response.ErrorResponse;
import com.payment.system.dto.response.ErrorResponseDto;
import com.payment.system.exception.BadRequestException;
import com.payment.system.logger.MainLogger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final MainLogger LOGGER = MainLogger.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        return buildResponseEntity(ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        Map<String, String> checks = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            checks.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse("Bad request", checks);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            @Nullable Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        return getResponseEntity(ex, status, request);
    }

    private ResponseEntity<Object> buildResponseEntity(Exception ex, WebRequest request) {
        HttpStatus status;
        if (ex instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST;
        }
        else if(ex instanceof AlreadyExistException){
            status=HttpStatus.ALREADY_REPORTED;
        }
        else if(ex instanceof NotFoundException){
            status=HttpStatus.NOT_FOUND;
        }
        else if(ex instanceof AuthenticationException){
            status=HttpStatus.UNAUTHORIZED;
        }
        else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        LOGGER.error("{} : {}", ex.getClass(), ex.getMessage());
        return getResponseEntity(ex, status, request);
    }

    private ResponseEntity<Object> getResponseEntity(Exception ex,
                                                     HttpStatus status,
                                                     WebRequest request) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode(status.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponseDto, status);
    }
}
