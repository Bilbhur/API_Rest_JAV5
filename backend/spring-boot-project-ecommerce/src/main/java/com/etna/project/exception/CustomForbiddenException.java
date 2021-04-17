package com.etna.project.exception;

import org.springframework.http.HttpStatus;

public class CustomForbiddenException extends CustomException{

    public CustomForbiddenException() {
        super();
        this.code = HttpStatus.FORBIDDEN;
        this.message = "Forbidden";
    }
}
