package com.example.bloggerBacked.bloggerBacked.user.controller;
import com.example.bloggerBacked.bloggerBacked.common.JwtUtil;
import com.example.bloggerBacked.bloggerBacked.user.model.User;
import com.example.bloggerBacked.bloggerBacked.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin // Optional, allows frontend calls
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    //Create User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


    //Get All User
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    //Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);  // Call service
        return ResponseEntity.ok("User deleted successfully");
    }


    //Update User
    @PutMapping("/{id}")
    public  User updateUser(@PathVariable UUID id,@RequestBody User user)
    {
        return userService.updateUser(id,user);
    }


    //Get Single User
    @GetMapping("/{id}")
    public  User getUser(@PathVariable UUID id)
    {
        return userService.getUser((id));
    }


    //Verify User
//    @GetMapping("/verify")
//    public ResponseEntity<?> getUser(@RequestParam String email, @RequestParam String password) {
//        User user = userService.verifyUser(email, password);
//
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//        }
//
//        return ResponseEntity.ok(user);
//    }

    @GetMapping("/verify")
    public ResponseEntity<?> getUser(@RequestParam String email, @RequestParam String password) {
        User user = userService.verifyUser(email, password);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail()); // or user.getEmail()
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user); // assuming your `User` doesn't expose password or sensitive info

        return ResponseEntity.ok(response);
    }
}
