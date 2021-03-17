package com.quest.etna.controller;

import com.quest.etna.config.JwtTokenUtil;
import com.quest.etna.model.JwtUserDetails;
import com.quest.etna.model.User;
import com.quest.etna.model.UserDetails;
import com.quest.etna.repositories.UserRepository;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody User user) {

        try {
            if (null == user.getUsername() || null == user.getPassword())
                return new ResponseEntity<>("{\"error\" : \"Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);

            User foundedUser = userRepository.findByUsername(user.getUsername());
            if (null != foundedUser)
                return new ResponseEntity<>("{\"error\" : \"Already exists\"}", HttpStatus.CONFLICT);

            user = userRepository.save(user);
            UserDetails userDetails = new UserDetails(user.getUsername(), user.getRole());
            return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> Authenticate(@RequestBody User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String token = jwtTokenUtil.generateToken(
                new JwtUserDetails(userRepository.findByUsername(user.getUsername())));
        return ResponseEntity.ok(token);
    }
}
