package com.example.bloggerBacked.bloggerBacked.user.controller;
import com.example.bloggerBacked.bloggerBacked.user.model.User;
import com.example.bloggerBacked.bloggerBacked.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);  // Call service
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/{id}")
    public  User updateUser(@PathVariable Long id,@RequestBody User user)
    {
        return userService.updateUser(id,user);
    }

    @GetMapping("/{id}")
    public  User getUser(@PathVariable Long id)
    {
        return userService.getUser((id));
    }
}
