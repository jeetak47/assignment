package com.uxpsystems.assignment.controller;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }

    @DeleteMapping
    public User deleteUser(@RequestBody User user){
        return userService.deleteUser(user.getId());
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id){
        user.setId(id);
        return userService.updateUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    }
