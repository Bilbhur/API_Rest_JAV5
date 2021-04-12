package com.etna.project.controller;

import com.etna.project.dao.CountryRepository;
import com.etna.project.entity.Country;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private CountryRepository countryRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Country> getList() {
        return countryRepository.findAll();
    }
}
