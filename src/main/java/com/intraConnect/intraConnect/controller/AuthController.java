package com.intraConnect.intraConnect.controller;


import com.intraConnect.intraConnect.controller.dto.AuthResponse;
import com.intraConnect.intraConnect.controller.dto.Response;
import com.intraConnect.intraConnect.entity.dto.LoginDto;
import com.intraConnect.intraConnect.entity.dto.UserDto;
import com.intraConnect.intraConnect.exception.EmailAlreadyExistException;
import com.intraConnect.intraConnect.service.AuthServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    private AuthServiceImp authService;

    public AuthController(AuthServiceImp authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto user) throws UsernameNotFoundException, AuthenticationException {
        return ResponseEntity.ok(authService.login(user));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto user)throws EmailAlreadyExistException {
        return ResponseEntity.ok(authService.register(user));
    }




}
