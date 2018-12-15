package com.uxpsystems.assignment.controller;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    @ResponseBody
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getUser(@PathVariable long id){
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseBody
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public User deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }

    @DeleteMapping
    @ResponseBody
    public User deleteUser(@RequestBody User user){
        return userService.deleteUser(user.getId());
    }

    @PutMapping("/{id}")
    @ResponseBody
    public User updateUser(@RequestBody User user, @PathVariable long id){
        user.setId(id);
        return userService.updateUser(user);
    }

    @PutMapping
    @ResponseBody
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    }
