package com.example.t2207a_springboot.service;

import com.example.t2207a_springboot.dto.request_model.LoginUser;
import com.example.t2207a_springboot.dto.request_model.RegisterUser;
import com.example.t2207a_springboot.entity.User;
import com.example.t2207a_springboot.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public User signup(RegisterUser input) {

        User user = new User()
                .setEmail(input.getEmail())
                .setFullName(input.getFullName()).setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);

    }


    public User authenticate(LoginUser input) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        User user = userRepository.findByEmail(input.getEmail());
        if (user == null)
            throw new UsernameNotFoundException("Email or password not correct");
        return user;


    }
}
