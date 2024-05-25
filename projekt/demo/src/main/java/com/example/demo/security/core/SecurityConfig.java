package com.example.demo.security.core;

import com.example.demo.service.uzivatelService;
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
    private uzivatelService uzivatelService;

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

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
        http.addFilterBefore(new AuthenticationFilter(uzivatelService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/fitness/api/authentication")
                .requestMatchers("/fitness/api/uzivatel")
                .requestMatchers("/fitness/api/uzivatel/all")
                .requestMatchers("/fitness/api/uzivatel/{id}")
                .requestMatchers("/fitness/api/uzivatel/update")
                .requestMatchers("/fitness/api/uzivatel/updatePassword")
                .requestMatchers("/fitness/api/uzivatel/updatePokrok")
                .requestMatchers("/fitness/api/uzivatel/change/{id}")
                .requestMatchers("/fitness/api/cvicenie")
                .requestMatchers("/fitness/api/cvicenie/{id}")
                .requestMatchers("/fitness/api/cvicenie/list")
                .requestMatchers("/fitness/api/cvicenie/delCvicenie")
                .requestMatchers("/fitness/api/treningovyPlan")
                .requestMatchers("/fitness/api/treningovyPlan/delete")
                .requestMatchers("/fitness/api/treningovyPlan/deleteInUser")
                .requestMatchers("/fitness/api/treningovyPlan/{id}")
                .requestMatchers("/fitness/api/treningovyPlan/list")
                .requestMatchers("/fitness/api/treningovyPlan/userList")
                .requestMatchers("/fitness/api/treningovyPlan/cvicenia/{id}")
                .requestMatchers("/fitness/api/treningovyPlan/cvicenia/getCviceniaByuser")
                .requestMatchers("/fitness/api/login")
                .requestMatchers("/fitness/api/typCvicenia")
                .requestMatchers("/fitness/api/typCvicenia/{id}")
                .requestMatchers("/fitness/api/typCvicenia/list")
                .requestMatchers("/fitness/api/uzivatel/{userId}/treningovyPlan/{planId}")
                .requestMatchers("/fitness/api/getUzivatelFromToken")
                .requestMatchers("fitness/api/uzivatel/treningovePlany/{id}")
                .requestMatchers("fitness/api/uzivatel/vaha")
                .requestMatchers("fitness/api/uzivatel/getVahy")
                .requestMatchers("fitness/api/uzivatel/pokrok/update")
                .requestMatchers("api/authentication/logout");
    }

}
