package com.todolist.ToDoList.service;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

@Service
public class JwtService {
    // Chave secreta usada para assinar o token (não deixar hardcoded em produção)
    private static final String SECRET_KEY = "minhaChaveSuperSecreta123minhaChaveSuperSecreta123"; // 32+ chars

    // Expiração do token (ex: 1 hora)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    // Gera o token a partir do username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // claim "sub"
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256) // <-- aqui!
                .compact();
    }

    // Valida se o token está correto e não expirou
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extrai o username do token
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(SECRET_KEY)
                            .parseClaimsJws(token)
                            .getBody();
        return claims.getSubject();
    }
}
