package com.example.bloggerBacked.bloggerBacked.user.service;
import com.example.bloggerBacked.bloggerBacked.user.model.User;
import com.example.bloggerBacked.bloggerBacked.user.repository.UserRepository;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }   else if (userRepository.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public  User updateUser(UUID id, User newUser)
    {
        User existingUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        existingUser.setUsername(newUser.getUsername());
        return userRepository.save(existingUser);
    }

    @Override
    public  User getUser (UUID id)
    {
        User targetUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        return targetUser;
    }


}
