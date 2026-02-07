package io.app.exceptions;

import io.app.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequiredFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleRequiredFieldException(RequiredFieldException ex){
        return ApiResponse.builder()
                .status(false)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleResourceNotFoundException(ResourceNotFoundException ex){
        return ApiResponse.builder()
                .status(false)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(DuplicateFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse handleDuplicateFoundException(DuplicateFoundException ex){
        return ApiResponse.builder()
                .status(false)
                .message(ex.getMessage())
                .build();
    }

}
