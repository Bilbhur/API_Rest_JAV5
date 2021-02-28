package com.quest.etna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping(value = "testSuccess")
    public String testSuccess() {
        return "success";
    }

    @GetMapping(value = "/testNotFound")
    public String testNotFound() {
        return "not found";
    }

    @GetMapping(value = "/testError")
    public String testError() {
        return "error";
    }
}
