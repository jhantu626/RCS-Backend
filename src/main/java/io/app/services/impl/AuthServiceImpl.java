package io.app.services.impl;

import io.app.dto.ApiResponse;
import io.app.dto.AuthResponse;
import io.app.dto.UserDto;
import io.app.exceptions.RequiredFieldException;
import io.app.exceptions.ResourceNotFoundException;
import io.app.model.User;
import io.app.repository.UserRepository;
import io.app.services.AuthService;
import io.app.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(JwtService jwtService,UserRepository repository,
                           AuthenticationManager authenticationManager,
                           BCryptPasswordEncoder passwordEncoder){
        this.jwtService=jwtService;
        this.repository=repository;
        this.authenticationManager=authenticationManager;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public ApiResponse createUser(UserDto userDto) {
        if (userDto.getUserName()==null || userDto.getPassword()==null || userDto.getRole()==null){
            throw new RequiredFieldException("Please Provide Required Field's");
        }

        User user=userDto.mapToUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser=repository.save(user);
        return ApiResponse.builder()
                .status(true)
                .message("User Created Successfully")
                .build();
    }

    @Override
    public AuthResponse login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        User user=repository.findUserByUserName(username)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Credentials"));
        boolean passwordMatching=passwordEncoder.matches(password,user.getPassword());
        if (!passwordMatching){
            throw new ResourceNotFoundException("Invalid Credential");
        }
        String token=jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .status(true)
                .build();
    }
}
