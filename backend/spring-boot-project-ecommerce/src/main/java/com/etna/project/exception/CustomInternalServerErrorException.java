package com.etna.project.exception;

import org.springframework.http.HttpStatus;

public class CustomInternalServerErrorException extends CustomException{

    public CustomInternalServerErrorException() {
        super();
        this.code = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "Something went wrong";
    }
}
