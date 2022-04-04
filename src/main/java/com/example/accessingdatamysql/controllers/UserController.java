package com.example.accessingdatamysql.controllers;

import com.example.accessingdatamysql.entities.User;
import com.example.accessingdatamysql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public String addNewUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        User u = new User();
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        userRepository.save(u);

        return "User has been successfully saved";
    }

    @GetMapping(path = "/all")
    public  Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
