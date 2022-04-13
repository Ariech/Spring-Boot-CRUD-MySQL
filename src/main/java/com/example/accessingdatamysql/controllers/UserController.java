package com.example.accessingdatamysql.controllers;

import com.example.accessingdatamysql.entities.User;
import com.example.accessingdatamysql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/users")
    public User addNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping(path = "/users")
    public  Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public  User getUser(@PathVariable Integer id) {
        Optional<User> userEntity = userRepository.findById(id);
        return userEntity.orElse(null);

    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Integer id) {
        return userRepository.findById(id).map( user -> {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        })
                .orElseGet(() -> {
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
