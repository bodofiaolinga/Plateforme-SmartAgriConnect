package com.example.SmartAgriConnect.security;

import com.example.SmartAgriConnect.model.User;
import com.example.SmartAgriConnect.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;



@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public final JwtService jwtService;
     public final UserRepository repository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader=request.getHeader("Authorisation");


        if (authHeader==null ||authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        String token=authHeader.substring(7);
        String email=jwtService.extractEmail(token);

        Optional<User> user=repository.findByEmail(email);

        UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(
                user,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_" +user.get().getRole()))
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request,response);



    }
}
