package io.app.controllers;

import io.app.dto.UserDto;
import io.app.services.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserServiceImpl service;

    public UserController(UserServiceImpl service){
        this.service=service;
    }

    @GetMapping("/profile")
    public UserDto profile(@RequestHeader("Authorization") String token){
        return service.profile(token);
    }

}
