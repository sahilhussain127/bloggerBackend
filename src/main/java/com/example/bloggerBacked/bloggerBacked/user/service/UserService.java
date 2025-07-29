package com.example.bloggerBacked.bloggerBacked.user.service;
import com.example.bloggerBacked.bloggerBacked.user.model.User;
import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    void deleteUserById(UUID id);
    User updateUser(UUID id, User user);
    User getUser(UUID id);
    User verifyUser(String email,String password);
    User getUserByEmail(String email);

}
