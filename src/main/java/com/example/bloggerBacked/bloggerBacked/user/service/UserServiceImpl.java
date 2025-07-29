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


    //Create User
    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }
        return userRepository.save(user);
    }


    //Get All User
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    //Delete User
    @Override
    public void deleteUserById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
    }


    //Update User
    @Override
    public  User updateUser(UUID id, User newUser)
    {
        User existingUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        existingUser.setFirstName(newUser.getFirstName());
        existingUser.setLastName(newUser.getLastName());
        return userRepository.save(existingUser);
    }


    //Get Single User
    @Override
    public  User getUser (UUID id)
    {
        User targetUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        return targetUser;
    }


    //Get Single User
    @Override
    public  User verifyUser (String email,String password)
    {
        User targetUser = userRepository.findByEmailAndPassword(email,password);

        return targetUser;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
