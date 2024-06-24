package com.im.controller;

import com.im.entities.User;
import com.im.helper.GeocodeResponse;
import com.im.helper.GeolocationService;
import com.im.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    // Get User Details by id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User u = userService.getUser(userId);
        return ResponseEntity.ok(u);
    }

}
