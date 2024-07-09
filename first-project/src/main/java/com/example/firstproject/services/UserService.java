package com.example.firstproject.services;

import com.example.firstproject.model.User;
import com.example.firstproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {
    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(String.format("Finding one user by name %s!", username));
        User user = repository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found!", username));
        }
        return user;
    }
}
