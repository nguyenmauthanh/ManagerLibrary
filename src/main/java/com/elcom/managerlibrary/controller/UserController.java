package com.elcom.managerlibrary.controller;

import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.User;
import com.elcom.managerlibrary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> listUser = userService.findAll();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @DeleteMapping("{theId}")
    public ResponseEntity<User> findUserById(@PathVariable Long theId){
        User user = userService.findById(theId);
        if(user == null){
            throw new NotFoundException(String.format("User has id %d not fount", theId));
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
