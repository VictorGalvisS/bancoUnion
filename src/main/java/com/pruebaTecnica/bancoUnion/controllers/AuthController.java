package com.pruebaTecnica.bancoUnion.controllers;


import com.pruebaTecnica.bancoUnion.config.securityConfig.JwtUtil;
import com.pruebaTecnica.bancoUnion.models.dto.Login;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Login logindto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(
                logindto.getUsername(), logindto.getPassword()
        );
        this.authenticationManager.authenticate(login);
        String jwt = this.jwtUtil.create(logindto.getUsername());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}
