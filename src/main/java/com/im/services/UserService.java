package com.im.services;

import com.im.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    //create
    User saveUser(User user);

    //get user by id
    User getUser(String userId);


}

