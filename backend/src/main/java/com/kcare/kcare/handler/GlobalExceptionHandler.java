package com.kcare.kcare.handler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(LockedException.class)
        public ResponseEntity<ExceptionResponse> handleException(LockedException exp) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                ExceptionResponse.builder()
                                                .businessErrorCode(businessErrorCode.ACCOUNT_LOCKED.getCode())
                                                .businessErrorDescription(
                                                                businessErrorCode.ACCOUNT_LOCKED.getDescription())
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(DisabledException.class)
        public ResponseEntity<ExceptionResponse> handleException(DisabledException exp) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                ExceptionResponse.builder()
                                                .businessErrorCode(businessErrorCode.ACCOUNT_DISABLED.getCode())
                                                .businessErrorDescription(
                                                                businessErrorCode.ACCOUNT_DISABLED.getDescription())
                                                .error(exp.getMessage())
                                                .build());

        }

        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exp) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                ExceptionResponse.builder()
                                                .businessErrorCode(businessErrorCode.BAD_CREDENTIALS.getCode())
                                                .businessErrorDescription(
                                                                businessErrorCode.BAD_CREDENTIALS.getDescription())
                                                .error(businessErrorCode.BAD_CREDENTIALS.getDescription())
                                                .build());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exp) {
                Map<String, String> validationErrors = new HashMap<>();
                exp.getBindingResult().getFieldErrors().forEach(error -> {
                        validationErrors.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                ExceptionResponse.builder()
                                                .validationErrors(validationErrors.entrySet().stream()
                                                                .map(entry -> entry.getKey() + ": " + entry.getValue())
                                                                .collect(Collectors.toSet()))
                                                .build());
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ExceptionResponse> handleException(ResourceNotFoundException exp) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription(exp.getMessage())
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ExceptionResponse> handleException(Exception exp) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Internal error, Contact the admin")
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(DuplicateEmailException.class)
        public ResponseEntity<ExceptionResponse> handleException(DuplicateEmailException exp) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Email already in use")
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(AccountNotVerfiedException.class)
        public ResponseEntity<ExceptionResponse> handleException(AccountNotVerfiedException exp) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Account Not Verified")
                                                .error(exp.getMessage())

                                                .build());
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ExceptionResponse> handleException(AccessDeniedException exp) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription(
                                                                "Access Denied")
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ExceptionResponse> handleException(EntityNotFoundException exp) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Resource Not Available in Database")
                                                .error(exp.getMessage())
                                                .build());
        }

        // @ExceptionHandler(ResponseStatusException.class)
        @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity<ExceptionResponse> handleException(ResponseStatusException exp) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Invalid Input")
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(MismatchedInputException.class)
        public ResponseEntity<ExceptionResponse> handleException(MismatchedInputException exp) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Invalid Input")
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(OutOfStockException.class)
        public ResponseEntity<ExceptionResponse> handleException(OutOfStockException exp) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Product Out of Stock")
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(InsufficientQuantityException.class)
        public ResponseEntity<ExceptionResponse> handleException(InsufficientQuantityException exp) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Insuffient Product available")
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(InvalidProductCategoryException.class)
        public ResponseEntity<ExceptionResponse> handleException(InvalidProductCategoryException exp) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                ExceptionResponse.builder()
                                                .error(exp.getMessage())
                                                .businessErrorDescription("Product not belong to consumable category")
                                                .build());
        }

        @ExceptionHandler(JsonMappingException.class)
        public ResponseEntity<ExceptionResponse> handleEnumParsingException(JsonMappingException ex) {
                if (ex.getCause() instanceof InvalidFormatException) {
                        InvalidFormatException invalidFormatEx = (InvalidFormatException) ex.getCause();
                        if (invalidFormatEx.getTargetType() != null && invalidFormatEx.getTargetType().isEnum()) {
                                String enumClassName = invalidFormatEx.getTargetType().getSimpleName();
                                String validValues = Arrays.stream(invalidFormatEx.getTargetType().getEnumConstants())
                                                .map(Object::toString)
                                                .collect(Collectors.joining(", "));

                                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                                .body(ExceptionResponse.builder()
                                                                .businessErrorDescription("Invalid Enum Value")
                                                                .error(String.format(
                                                                                "Invalid value for %s. Accepted values are: [%s]",
                                                                                enumClassName, validValues))
                                                                .build());
                        }
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ExceptionResponse.builder()
                                                .businessErrorDescription("Invalid Input")
                                                .error(ex.getMessage())
                                                .build());
        }

}