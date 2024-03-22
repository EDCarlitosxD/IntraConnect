package com.intraConnect.intraConnect.service;

import com.intraConnect.intraConnect.controller.dto.AuthResponse;
import com.intraConnect.intraConnect.entity.UserEntity;
import com.intraConnect.intraConnect.entity.dto.LoginDto;
import com.intraConnect.intraConnect.entity.dto.UserDto;
import com.intraConnect.intraConnect.enums.Departamento;
import com.intraConnect.intraConnect.exception.EmailAlreadyExistException;
import com.intraConnect.intraConnect.repositoy.UserRepository;
import com.intraConnect.intraConnect.service.interfaces.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImp  implements AuthService {

    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    public AuthServiceImp(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager,PasswordEncoder passwordEncoder){
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse login(LoginDto user) throws UsernameNotFoundException, AuthenticationException {

        Optional<UserEntity> userFind =  userRepository.findByEmail(user.getEmail());

        if(userFind.isEmpty())throw new  UsernameNotFoundException("El usuario no existe");

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(auth);


        UserEntity userGet = userFind.get();

        String token = jwtService.generateToken(userGet);


        return new AuthResponse("Your Token",token);
    }



    @Override
    public AuthResponse register(UserDto user)throws EmailAlreadyExistException {
        Optional<UserEntity> findUser = userRepository.findByEmail(user.getEmail());

        if(findUser.isPresent())throw new EmailAlreadyExistException("El email ya existe");

        UserEntity userEntity = new UserEntity(null,
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getName(),
                user.getEmail(),
                true,
                user.getDepartamento()

        );


        UserEntity saveUser = userRepository.save(userEntity);
        String token = jwtService.generateToken(saveUser);

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        ));
          SecurityContextHolder.getContext().setAuthentication(auth);


        return new AuthResponse("Registro exitoso",token);
    }

}
