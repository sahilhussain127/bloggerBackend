package com.example.bloggerBacked.bloggerBacked.user.repository;
import com.example.bloggerBacked.bloggerBacked.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
