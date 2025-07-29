package com.example.bloggerBacked.bloggerBacked.user.controller;
import com.example.bloggerBacked.bloggerBacked.common.JwtUtil;
import com.example.bloggerBacked.bloggerBacked.user.model.User;
import com.example.bloggerBacked.bloggerBacked.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    @PostMapping("/verify")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);

        // Generate token after user is created
        String token = jwtUtil.generateToken(createdUser.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", createdUser);

        return ResponseEntity.ok(response);
    }


    //Get All User
    @GetMapping("/listAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    //Delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);  // Call service
        return ResponseEntity.ok("User deleted successfully");
    }


    //Update User
    @PutMapping("/update/{id}")
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

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // Remove "Bearer "
        String email = jwtUtil.extractUsername(token); // You stored email in token

        User user = userService.getUserByEmail(email); // Add this method if not exist

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Optionally create a DTO to hide password
        return ResponseEntity.ok(user);
    }

}
