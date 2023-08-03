package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        LOGGER.info("[loadUserByUsername] loadUserByUsername 수행. Username: {}", username);
        return userRepository.getByUserid(username);
    }
}
