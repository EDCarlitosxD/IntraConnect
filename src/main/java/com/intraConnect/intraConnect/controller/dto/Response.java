package com.intraConnect.intraConnect.controller.dto;

import lombok.Data;

@Data
public abstract class Response {

    private String message;

    public Response(String message){
        this.message = message;
    }

}
