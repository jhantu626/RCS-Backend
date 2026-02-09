package io.app.services.impl;

import io.app.dto.UserDto;
import io.app.exceptions.ResourceNotFoundException;
import io.app.model.User;
import io.app.repository.UserRepository;
import io.app.services.JwtService;
import io.app.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository repository,JwtService jwtService){
        this.repository=repository;
        this.jwtService=jwtService;
    }

    @Override
    public UserDto profile(String token) {
        String username=jwtService.extractUsername(jwtService.extractToken(token));
        Optional<User> optionalUser = repository.findUserByUserName(username);
        if (optionalUser.isEmpty()){
            throw new ResourceNotFoundException("Invalid Token");
        }
        User user=optionalUser.get();
        UserDto userDto=UserDto.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .role(user.getRole())
                .billingType(user.getBillingType())
                .build();

        return userDto;
    }
}
