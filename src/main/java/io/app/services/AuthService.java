package io.app.services;

import io.app.dto.ApiResponse;
import io.app.dto.AuthResponse;
import io.app.dto.UserDto;

public interface AuthService {
    public ApiResponse createUser(UserDto userDto);
    public AuthResponse login(String username, String password);
}
