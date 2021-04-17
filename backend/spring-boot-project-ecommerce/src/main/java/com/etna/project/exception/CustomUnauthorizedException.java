package com.etna.project.exception;

import org.springframework.http.HttpStatus;

public class CustomUnauthorizedException extends CustomException{

    public CustomUnauthorizedException() {
        super();
        this.code = HttpStatus.UNAUTHORIZED;
        this.message = "Unauthorized";
    }
}
