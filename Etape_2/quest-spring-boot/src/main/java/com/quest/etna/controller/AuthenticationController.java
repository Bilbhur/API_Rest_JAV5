package com.quest.etna.controller;

import com.quest.etna.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    private static UserRepository _userRepository;

    @PostMapping(value = "/register")
    @ResponseStatus(code = HttpStatus.OK)
    public String testSuccess(@RequestBody String username, @RequestBody String password) {

        return "success" + username + " / " + password;
    }
}
