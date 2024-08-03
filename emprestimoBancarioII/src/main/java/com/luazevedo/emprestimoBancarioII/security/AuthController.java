package com.luazevedo.emprestimoBancarioII.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Lógica de autenticação
        // Após autenticar, gere um token JWT
        String jwt = jwtProvider.generateToken(loginRequest.getUsername());
        return jwt;
    }
}