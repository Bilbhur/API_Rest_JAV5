package com.etna.project.exception;

import org.springframework.http.HttpStatus;

public class CustomConflictException extends CustomException{

    public CustomConflictException() {
        super();
        this.code = HttpStatus.CONFLICT;
        this.message = "Resource already exists";
    }
}
