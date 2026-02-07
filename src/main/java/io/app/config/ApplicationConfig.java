package io.app.config;

import io.app.exceptions.ResourceNotFoundException;
import io.app.repository.UserRepository;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ApplicationConfig {
    private final UserRepository repository;

    public ApplicationConfig(UserRepository repository){
        this.repository=repository;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return (username)->repository.findUserByUserName(username)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Credentials"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=
                new DaoAuthenticationProvider(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration, ServletContextInitializer servletContextInitializer){
        return configuration.getAuthenticationManager();
    }

}
