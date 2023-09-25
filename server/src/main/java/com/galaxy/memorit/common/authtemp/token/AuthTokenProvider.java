package com.galaxy.memorit.common.authtemp.token;

import com.galaxy.memorit.common.authtemp.entity.Role;
import com.galaxy.memorit.common.authtemp.exception.InvalidTokenException;
import com.galaxy.memorit.common.authtemp.exception.NotExpiredTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthTokenProvider {

    private final Key key;
    private static final String AUTHORITIES_KEY = "role";

    public AuthTokenProvider(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public AuthToken createRefreshToken(String tokenSecret, long expiry) {
        Date expiration = new Date(new Date().getTime() + expiry);
        return AuthToken.createAuthToken(tokenSecret, null, expiration, key);
    }

    public AuthToken createAccessToken(String providerId, Role role, long expiry) {
        Date expiration = new Date(new Date().getTime() + expiry);
        return AuthToken.createAuthToken(providerId, role, expiration, key);
    }

    public void validateAccessTokenBeforeReissue(String token) {
        if (!isValidate(token)) {
            throw new InvalidTokenException();
        }

        if (!isExpired(token)) {
            throw new NotExpiredTokenException();
        }
    }

    public void validateRefreshToken(String token) {
        if (!isValidate(token)) {
            throw new InvalidTokenException();
        }
    }

    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, key);
    }

    public boolean isValidate(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isExpired(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            return true;
        }
        return false;
    }

    public Date getExpiration(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        } catch (Exception e) {
            return null;
        }
    }

    public String getExpiredTokenProviderId(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
        return null;
    }

    public Role getExpiredTokenRole(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            return Role.of(e.getClaims().get("role", String.class));
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        AuthToken authToken = convertAuthToken(token);
        Claims claims = authToken.getTokenClaims();
        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(new String[]{claims.get(AUTHORITIES_KEY).toString()})
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, authToken, authorities);
    }
}
