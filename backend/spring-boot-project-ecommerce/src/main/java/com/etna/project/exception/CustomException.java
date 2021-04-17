package com.etna.project.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

    protected HttpStatus code;
    protected String message;

    public CustomException() {
        super();
    }

    public HttpStatus getCode(){
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
