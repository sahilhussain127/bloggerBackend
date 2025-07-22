package com.example.bloggerBacked.bloggerBacked.repository;
import com.example.bloggerBacked.bloggerBacked.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
