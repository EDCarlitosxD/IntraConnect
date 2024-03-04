package com.intraConnect.intraConnect.controller.dto;

import lombok.Data;
import lombok.Getter;


public class AuthResponse extends Response {

    @Getter
    private String token;
    public AuthResponse(String message,String token) {
        super(message);
        this.token = token;
    }
}
