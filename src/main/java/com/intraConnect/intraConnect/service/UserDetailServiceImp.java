package com.intraConnect.intraConnect.service;

import com.intraConnect.intraConnect.repositoy.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return  userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("El email no existe"));

    }
}
