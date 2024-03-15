package com.wayon.controllers;

import com.wayon.domain.user.User;
import com.wayon.dtos.UserDto;
import com.wayon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto user) {
        User newUser = this.userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) throws Exception {
        User user = this.userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{account}")
    public ResponseEntity<User> updateUser(@RequestBody UserDto user, @PathVariable String account) {
        User updatedUser = userService.getUserByAccount(account);

        updatedUser.account = user.account;
        updatedUser.name = user.name;
        updatedUser.balance = user.balance;

        userService.updateUser(updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
