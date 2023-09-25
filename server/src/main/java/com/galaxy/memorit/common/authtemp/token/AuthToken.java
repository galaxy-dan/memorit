package com.galaxy.memorit.common.authtemp.token;

import com.galaxy.memorit.common.authtemp.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthToken {

    @Getter
    private final String token;
    private final Key key;

    private static final String AUTHORITIES_KEY = "role";

    @Builder
    public AuthToken(String subject, Role role, Date expiry, Key key) {
        this.key = key;
        this.token = createToken(subject, role, expiry);
    }

    public static AuthToken createAuthToken(String subject, Role role, Date expiry, Key key) {
        return AuthToken.builder()
            .subject(subject)
            .role(role)
            .expiry(expiry)
            .key(key)
            .build();
    }

    private String createToken(String subject, Date expiry) {
        return Jwts.builder()
            .setSubject(subject)
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(expiry)
            .compact();
    }

    private String createToken(String subject, Role role, Date expiry) {
        if (role == null) {
            return createToken(subject, expiry);
        }

        return Jwts.builder()
            .setSubject(subject)
            .claim(AUTHORITIES_KEY, role.getCode())
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(expiry)
            .compact();
    }

    public Claims getTokenClaims() {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
