package com.example.firstproject.services;

import com.example.firstproject.data.DTO.v1.RegisterDTO;
import com.example.firstproject.data.DTO.v1.UserDTO;
import com.example.firstproject.mapper.Mapper;
import com.example.firstproject.model.User;
import com.example.firstproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDetails findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public UserDTO register(RegisterDTO data) {
        String passwordEncoder = new BCryptPasswordEncoder().encode(data.getPassword());
        User entity = new User(data.getLogin(), passwordEncoder, data.getRole());
        return Mapper.parseObject(repository.save(entity), UserDTO.class);
    }
}
