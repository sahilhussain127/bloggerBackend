package com.example.bloggerBacked.bloggerBacked.user.controller;
import com.example.bloggerBacked.bloggerBacked.user.model.User;
import com.example.bloggerBacked.bloggerBacked.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);  // Call service
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/{id}")
    public  User updateUser(@PathVariable UUID id,@RequestBody User user)
    {
        return userService.updateUser(id,user);
    }

    @GetMapping("/{id}")
    public  User getUser(@PathVariable UUID id)
    {
        return userService.getUser((id));
    }
}
