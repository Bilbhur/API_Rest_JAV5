package com.etna.project.controller;

import com.etna.project.config.JwtTokenUtil;
import com.etna.project.config.JwtUserDetailsService;
import com.etna.project.entity.JwtUserDetails;
import com.etna.project.entity.User;
import com.etna.project.entity.UserDetails;
import com.etna.project.dao.UserRepository;
import com.etna.project.exception.CustomConflictException;
import com.etna.project.exception.CustomInternalServerErrorException;
import com.etna.project.exception.CustomUnauthorizedException;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody User user) {
        if (null == user.getUsername() || null == user.getPassword())
            throw new CustomInternalServerErrorException();

        User foundedUser = userRepository.getByUsername(user.getUsername());
        if (null != foundedUser)
            throw new CustomConflictException();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);
        UserDetails userDetails = new UserDetails(user.getUsername(), user.getRole());
        return new ResponseEntity<>(userDetails, HttpStatus.CREATED);

    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        try {
            UsernamePasswordAuthenticationToken tok = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authenticationManager.authenticate(tok);

            JwtUserDetails jwtUD = new JwtUserDetails(userRepository.getByUsername(user.getUsername()));
            String token = jwtTokenUtil.generateToken(jwtUD);

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("token", token);

            return ResponseEntity.ok(body);
        } catch (Exception e) {
            throw new CustomInternalServerErrorException();
        }

    }

    @GetMapping(value = "/me")
    public ResponseEntity<?> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null == auth)
            throw new CustomUnauthorizedException();

        User u = userRepository.getByUsername(auth.getPrincipal().toString());

        UserDetails userDetails = new UserDetails(u.getUsername(), u.getRole());

        if (null != userDetails) {
            return ResponseEntity.ok(userDetails);
        }
        else {
            throw new CustomInternalServerErrorException();
        }
    }
}
