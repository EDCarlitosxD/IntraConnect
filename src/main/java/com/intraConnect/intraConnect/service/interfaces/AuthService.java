package com.intraConnect.intraConnect.service.interfaces;

import com.intraConnect.intraConnect.exception.EmailAlreadyExistException;
import com.intraConnect.intraConnect.controller.dto.AuthResponse;
import com.intraConnect.intraConnect.entity.dto.LoginDto;
import com.intraConnect.intraConnect.entity.dto.UserDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService {

    AuthResponse login(LoginDto user)throws UsernameNotFoundException;
    AuthResponse register(UserDto user)throws EmailAlreadyExistException;
}
