package com.etna.project.controller;

import com.etna.project.entity.Product;
import com.etna.project.entity.ProductCategory;
import com.etna.project.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/product-category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductCategory> getList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        return productCategoryService.getList(page, limit);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductCategory> getOneById(@PathVariable("id") Integer id) {
        return new ResponseEntity(productCategoryService.getOneById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('{ROLE_USER, ROLE_ADMIN}')")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        productCategory = productCategoryService.create(productCategory);
        return new ResponseEntity<>(productCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteProductCategory(@PathVariable("id") Integer id) {
        return productCategoryService.delete(id);
    }
}
