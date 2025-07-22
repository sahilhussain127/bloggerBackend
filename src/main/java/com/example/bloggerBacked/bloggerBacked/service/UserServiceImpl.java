package com.example.bloggerBacked.bloggerBacked.service;
import com.example.bloggerBacked.bloggerBacked.model.User;
import com.example.bloggerBacked.bloggerBacked.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        // You can add validation if needed
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
