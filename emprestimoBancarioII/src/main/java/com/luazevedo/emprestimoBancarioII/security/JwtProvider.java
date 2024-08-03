package com.luazevedo.emprestimoBancarioII.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private String jwtSecret = "sua-chave-secreta";  // Substitua por sua chave secreta real
    private int jwtExpirationInMs = 86400000; // 24 horas em milissegundos

    // Método para gerar o token JWT
    public String generateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Método para validar o token JWT
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            // Logger ou manipulação para assinatura inválida
            System.out.println("Assinatura JWT inválida");
        } catch (MalformedJwtException ex) {
            // Logger ou manipulação para token malformado
            System.out.println("Token JWT malformado");
        } catch (ExpiredJwtException ex) {
            // Logger ou manipulação para token expirado
            System.out.println("Token JWT expirado");
        } catch (UnsupportedJwtException ex) {
            // Logger ou manipulação para token não suportado
            System.out.println("Token JWT não suportado");
        } catch (IllegalArgumentException ex) {
            // Logger ou manipulação para argumento ilegal
            System.out.println("A string de reivindicações JWT está vazia.");
        }
        return false;
    }

    // Método para obter o username a partir do token JWT
    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}