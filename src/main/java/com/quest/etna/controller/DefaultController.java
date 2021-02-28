package com.quest.etna.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping(value = "testSuccess")
    @ResponseStatus(code = HttpStatus.OK)
    public String testSuccess() {
        return "success";
    }

    @GetMapping(value = "/testNotFound")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String testNotFound() {
        return "not found";
    }

    @GetMapping(value = "/testError")
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public String testError() {
        return "error";
    }
}
