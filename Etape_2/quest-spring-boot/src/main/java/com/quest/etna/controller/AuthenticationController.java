package com.quest.etna.controller;

import com.quest.etna.model.User;
import com.quest.etna.model.UserDetails;
import com.quest.etna.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository _userRepository;

    @PostMapping(value = "/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UserDetails> register(@RequestBody User user) {

        User foundedUser = _userRepository.getUserByUsername(user.getUsername());
        if (null != foundedUser)
            return new ResponseEntity("Already exist", HttpStatus.CONFLICT);

        try {
            user = _userRepository.save(user);
            UserDetails userDetails = new UserDetails(user.getUsername(), user.getRole());
            return new ResponseEntity(userDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
