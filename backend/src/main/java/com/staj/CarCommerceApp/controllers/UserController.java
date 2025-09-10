package com.staj.CarCommerceApp.controllers;

import com.staj.CarCommerceApp.entities.User;
import com.staj.CarCommerceApp.services.LoggingService;
import com.staj.CarCommerceApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoggingService loggingService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user = userService.registerUser(user);
        if (user == null) {
            loggingService.logError("User already exists!");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        String JWTToken = userService.verifyUser(user);
        if (JWTToken == null) {
            loggingService.logInfo("Credentials are incorrect");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", JWTToken);
        return new ResponseEntity<Map<String,String>>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        if (user == null) {
            loggingService.logError("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<User> addUserRole(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
        User user = userService.addUserRole(userId,roleId);
        if (user == null) {
            loggingService.logError("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id){
        if (!userService.deleteUserById(id)) {
            loggingService.logError("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
