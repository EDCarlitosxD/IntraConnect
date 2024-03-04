package com.intraConnect.intraConnect.exception.dto;

import com.intraConnect.intraConnect.controller.dto.Response;
import org.springframework.http.HttpStatus;

public class UserNotFounExceptiondDto extends Response {

    public HttpStatus status;

    public UserNotFounExceptiondDto(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }


}
