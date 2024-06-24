package com.im.services.impl;

import com.im.entities.User;
import com.im.exceptions.UserNotFoundException;
import com.im.helper.GeocodeResponse;
import com.im.helper.GeolocationService;
import com.im.repositories.UserRepository;
import com.im.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GeolocationService geolocationService;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public User saveUser(User user) {
        String id = UUID.randomUUID().toString();
        String pincode = user.getPincode();
        user.setId(id);
        // setting city and country through pincode
        GeocodeResponse.Result result = geolocationService.getLocationByPincode(pincode);
        user.setCity(geolocationService.getCityFromResponse(result));
        user.setCountry(geolocationService.getCountryFromResponse(result));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {
        User u = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found!!"));
        System.out.println(u);
        return u;
    }


}


