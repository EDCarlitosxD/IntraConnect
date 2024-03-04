package com.intraConnect.intraConnect.exception.dto;

import com.intraConnect.intraConnect.controller.dto.Response;
import org.springframework.http.HttpStatus;

public class GenericDtoResponse extends Response {

    public HttpStatus httpStatus;
    public GenericDtoResponse(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
