package com.im.config;

import com.im.entities.User;
import com.im.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from database
        User user =userRepository.findByEmail(username).orElseThrow(()->new RuntimeException("User not found Exception"));
        if(user == null) {
            throw new UsernameNotFoundException("Could not found user !!");
        }
        UserDetails ud = new CustomUserDetails(user);
        return ud;
    }
}

