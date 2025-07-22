package com.example.bloggerBacked.bloggerBacked.controller;
import com.example.bloggerBacked.bloggerBacked.model.User;
import com.example.bloggerBacked.bloggerBacked.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin // Optional, allows frontend calls
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
