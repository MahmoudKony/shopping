package com.kony.shopping.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleException(
            ResourceNotFoundException ne,
            HttpServletRequest servletRequest
    ){

        ApiError apiError = new ApiError(
                servletRequest.getRequestURI(),
                ne.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDate.now().atStartOfDay()
        );
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e,
                                                    HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage()+":LL)",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
