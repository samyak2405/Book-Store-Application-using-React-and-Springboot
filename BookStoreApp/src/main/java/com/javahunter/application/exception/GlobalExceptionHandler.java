package com.javahunter.application.exception;

import com.javahunter.application.config.ApiMessages;
import com.javahunter.application.dto.ApiResponses;
import com.javahunter.application.exception.impl.ResourceNotFoundException;
import com.javahunter.application.exception.impl.UserNotEnabledException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    private final ApiResponses apiResponse;
    private final ApiMessages apiMessages;
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setSuccess(false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotEnabledException.class)
    public ResponseEntity<?> handleUserNotEnabledException(UserNotEnabledException ex) {
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setSuccess(false);
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setSuccess(false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        if (Objects.requireNonNull(ex.getRequiredType()).isEnum()) {
            var enumClass = (Class<? extends Enum<?>>) ex.getRequiredType();
            String validEnumValues = Arrays.stream(enumClass.getEnumConstants())
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));
            apiResponse.setMessage(String.format(apiMessages.getMessage("error.type.mismatch"), ex.getValue(), validEnumValues));
        } else {
            apiResponse.setMessage(String.format(apiMessages.getMessage("error.type.mismatch"), ex.getValue(), ex.getRequiredType().isEnum()));
        }
        apiResponse.setSuccess(false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errorResponse.put(fieldName, message);
                });

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
