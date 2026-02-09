package io.app.services;

import io.app.dto.UserDto;

public interface UserService {
    UserDto profile(String token);
}
