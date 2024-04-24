package com.example.demo.security.core;

import com.example.demo.service.uzivatelDTO;
import com.example.demo.service.uzivatelService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.security.service.AuthenticationService;
import com.example.demo.security.service.UserRolesDto;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final uzivatelService uzivatelService;

    public AuthenticationFilter(uzivatelService uzivatelService) {
        this.uzivatelService = uzivatelService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if ( !StringUtils.hasLength(authHeader) || ! authHeader.startsWith("Bearer ")) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }

        String token = authHeader.substring("Bearer".length()).trim();
        uzivatelDTO user = uzivatelService.getUzivatelFromToken(token);

        List<SimpleGrantedAuthority> roles = user.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        UsernamePasswordAuthenticationToken auth
                = new UsernamePasswordAuthenticationToken(user.getUsername(), null, roles);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

}
