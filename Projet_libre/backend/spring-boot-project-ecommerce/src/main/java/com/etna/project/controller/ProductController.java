package com.etna.project.controller;

import com.etna.project.entity.Country;
import com.etna.project.entity.Product;
import com.etna.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/products/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        return productService.getList(page, limit);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> getOneById(@PathVariable("id") Integer id) {
        return new ResponseEntity(productService.getOneById(id), HttpStatus.OK);
    }


}
