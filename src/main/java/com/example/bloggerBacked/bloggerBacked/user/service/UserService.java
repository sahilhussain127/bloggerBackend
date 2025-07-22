package com.example.bloggerBacked.bloggerBacked.user.service;
import com.example.bloggerBacked.bloggerBacked.user.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    void deleteUserById(Long id);
    User updateUser(Long id,User user);
    User getUser(Long id);

}
