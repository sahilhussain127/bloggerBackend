package com.example.bloggerBacked.bloggerBacked.service;
import com.example.bloggerBacked.bloggerBacked.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
}
