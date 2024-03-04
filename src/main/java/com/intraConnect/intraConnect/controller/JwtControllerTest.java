package com.intraConnect.intraConnect.controller;

import com.intraConnect.intraConnect.service.JwtService;
import com.intraConnect.intraConnect.service.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/jwt")
public class JwtControllerTest {


    @Autowired
    JwtService jwtService;

    @Autowired
    UserDetailServiceImp userDetailServiceImp;


    @GetMapping("/token")
    public String getToken(){
        return  jwtService.generateToken("Username",2l);
    }


}
