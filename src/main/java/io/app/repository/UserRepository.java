package io.app.repository;

import io.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findUserByUserName(String username);
    public boolean existsUserByUserName(String username);
}
