package com.intraConnect.intraConnect.exception;

import com.intraConnect.intraConnect.controller.dto.Response;
import com.intraConnect.intraConnect.exception.dto.GenericDtoResponse;
import com.intraConnect.intraConnect.exception.dto.UserNotFounExceptiondDto;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAuth {


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response> usernameNotFoundException(UsernameNotFoundException exception){

        var dto = new UserNotFounExceptiondDto("El usuario no existe",HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }


    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<GenericDtoResponse> emailAlreadyExistException(EmailAlreadyExistException exception){

        var dto = new GenericDtoResponse("El email ya existe",HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GenericDtoResponse> authenticationException(AuthenticationException exception){
        var dto = new GenericDtoResponse("Las credenciales son incorrectas",HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }
}
