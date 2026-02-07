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

}
