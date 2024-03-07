package com.intraConnect.intraConnect.configuration.filter;

import com.intraConnect.intraConnect.service.JwtService;
import com.intraConnect.intraConnect.service.UserDetailServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserDetailServiceImp userDetailService;

    public JwtFilter(JwtService jwtService, UserDetailServiceImp userDetailService){
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuthorization = request.getHeader("Authorization");
        String jwt;
        String email;

        if(headerAuthorization != null && headerAuthorization.startsWith("Bearer")){
            String token = headerAuthorization.substring(7);

            if(jwtService.validToken(token)){
                email = jwtService.getEmailClaim(token);
                UserDetails detail = userDetailService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        detail.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }


        }


        filterChain.doFilter(request,response);
    }
}
