package com.etna.project.exception;

import org.springframework.http.HttpStatus;

public class CustomResourceNotFoundException extends CustomException{

    public CustomResourceNotFoundException() {
        super();
        this.code = HttpStatus.NOT_FOUND;
        this.message = "Resource not found";
    }
}
