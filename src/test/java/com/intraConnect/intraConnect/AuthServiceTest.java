package com.intraConnect.intraConnect;

import com.intraConnect.intraConnect.controller.dto.AuthResponse;
import com.intraConnect.intraConnect.entity.UserEntity;
import com.intraConnect.intraConnect.entity.dto.LoginDto;
import com.intraConnect.intraConnect.entity.dto.UserDto;
import com.intraConnect.intraConnect.repositoy.UserRepository;
import com.intraConnect.intraConnect.service.AuthServiceImp;
import com.intraConnect.intraConnect.service.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@SpringBootTest
public class AuthServiceTest {

    @InjectMocks
    AuthServiceImp authServiceImp;


    @Mock
    UserRepository userRepository;

    @Mock
    JwtService jwtService;

    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void loginSuccess(){
        //DTO TEST
        LoginDto loginDto = new LoginDto("juan@gmail.com","123");
        //ENTITY TEST
        UserEntity entity = new UserEntity(
                1L,
                "EDCarlitosxD",
                "123",
                "Juan Carlos",
                "juan@gmail.com",
                true);

        Optional<UserEntity> entityOptional = Optional.of(entity);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginDto.getEmail(),
                loginDto.getPassword()
        );

        //Mockito return when
        Mockito.when(authenticationManager.authenticate(authenticationToken)).thenReturn(authenticationToken);
        Mockito.when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(entityOptional);
        Mockito.when(jwtService.generateToken(entity)).thenReturn("token");

        //Execution the method
        AuthResponse response = authServiceImp.login(loginDto);

        //Testing
        assertEquals("Your Token",response.getMessage());
        assertEquals("token",response.getToken());
    }

   @Test
    void login_usernameNotFoundException(){
            //DTO TEST
            LoginDto loginDto = new LoginDto("juan@gmail.com","123");
            //ENTITY TEST
            UserEntity entity = new UserEntity(
                    1L,
                    "EDCarlitosxD",
                    "123",
                    "Juan Carlos",
                    "jua@gmail.com",
                    true);

            Optional<UserEntity> entityOptional = Optional.empty();

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
            );





            //Mockito return when
            Mockito.when(authenticationManager.authenticate(authenticationToken)).thenReturn(authenticationToken);
            Mockito.when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(entityOptional);

            //Execution the method

            //Testing
            assertThrows(UsernameNotFoundException.class,()->{
                AuthResponse login = authServiceImp.login(loginDto);
            });


    }
    @Test
    void register_success() {
        UserDto dto = new UserDto(null, "Juanito",
                "password123",
                "Juan Carlos",
                "juan@gmail.com");


        UserEntity userEntity = new UserEntity(null,
                dto.getUsername(),
                "password",
                dto.getName(),
                dto.getEmail(),
                true
        );

        UsernamePasswordAuthenticationToken tokenPassword = new UsernamePasswordAuthenticationToken(
                userEntity.getEmail(),
                userEntity.getPassword()
        );
        Authentication authentication = Mockito.mock(Authentication.class);

        Mockito.when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode(dto.getPassword())).thenReturn("password");
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(jwtService.generateToken(userEntity)).thenReturn("token");
        //Mockito.when(authenticationManager.authenticate(tokenPassword)).thenReturn(authentication);






        AuthResponse response = authServiceImp.register(dto);

        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(dto.getEmail());
        Mockito.verify(passwordEncoder,Mockito.times(1)).encode(dto.getPassword());
        Mockito.verify(userRepository,Mockito.times(1)).save(userEntity);
        Mockito.verify(jwtService,Mockito.times(1)).generateToken(userEntity);

        assertEquals("Registro exitoso",response.getMessage());
        assertEquals("token",response.getToken());
    }

}
