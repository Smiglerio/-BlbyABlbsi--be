package com.example.demo.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.demo.security.service.AuthenticationService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/authentication").permitAll()
                .anyRequest().authenticated());
//        http.exceptionHandling((exception)-> exception.authenticationEntryPoint(authEntryPoint).accessDeniedPage("/error/accedd-denied"));
        http.exceptionHandling((exception)-> exception.authenticationEntryPoint(authEntryPoint));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(new AuthenticationFilter(authenticationService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/fitness/api/authentication")
                .requestMatchers("/fitness/api/uzivatel")
                .requestMatchers("/fitness/api/uzivatel/all")
                .requestMatchers("/fitness/api/uzivatel/{id}")
                .requestMatchers("/fitness/api/cvicenie")
                .requestMatchers("/fitness/api/cvicenie/{id}")
                .requestMatchers("/fitness/api/cvicenie/list")
                .requestMatchers("/fitness/api/treningovyPlan")
                .requestMatchers("/fitness/api/treningovyPlan/{id}")
                .requestMatchers("/fitness/api/treningovyPlan/list")
                .requestMatchers("/fitness/api/treningovyPlan/cvicenia/{id}")
                .requestMatchers("/fitness/api/login")
                .requestMatchers("/fitness/api/typCvicenia")
                .requestMatchers("/fitness/api/typCvicenia/{id}")
                .requestMatchers("/fitness/api/typCvicenia/list");
    }

}
