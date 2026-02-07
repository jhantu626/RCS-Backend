package io.app.controllers;

import io.app.dto.ApiResponse;
import io.app.dto.AuthResponse;
import io.app.dto.UserDto;
import io.app.model.User;
import io.app.services.impl.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServiceImpl service;

    public AuthController(AuthServiceImpl service){
        this.service=service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse createUser(@RequestBody UserDto userDto){
        return service.createUser(userDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@RequestParam String username,
                              @RequestParam String password){
        return service.login(username,password);
    }


}
