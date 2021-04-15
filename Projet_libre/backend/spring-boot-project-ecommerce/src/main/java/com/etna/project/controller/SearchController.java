package com.etna.project.controller;

import com.etna.project.dao.SearchRepository;
import com.etna.project.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class SearchController {

    @Autowired
    private SearchRepository searchRepository;

    @GetMapping("/search/findByCategoryId")
    @ResponseStatus(HttpStatus.OK)
    public Page<Product> getListByCategoryId(@RequestParam(defaultValue = "1") Long id,
                                             @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        return searchRepository.findByCategoryId(id, pageable);
    }

    @GetMapping("/search/findByNameContaining")
    @ResponseStatus(HttpStatus.OK)
    public Page<Product> getListByNameContaining(@RequestParam(required = false) String name,
                                             @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        return searchRepository.findByNameContaining(name, pageable);
    }
}
