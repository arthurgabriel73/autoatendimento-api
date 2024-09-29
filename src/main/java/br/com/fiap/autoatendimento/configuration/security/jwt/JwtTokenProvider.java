package br.com.fiap.autoatendimento.configuration.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import br.com.fiap.autoatendimento.configuration.security.TokenProvider;

@Service
public class JwtTokenProvider implements TokenProvider {
    
    @Value("${jwt.secret.key}")
    private String SECRET;

    public String generateToken(String cpf) {
        return Jwts.builder()
                .setSubject(cpf)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}