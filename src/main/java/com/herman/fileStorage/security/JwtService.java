package com.herman.fileStorage.security;

import com.herman.fileStorage.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    /**
     * Secret key used to sign JWT tokens.
     * min 256 byte key length
     */
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Token expiration in milliseconds, 24h
     */
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    /**
     * Generates a JWT token for a given username.
     *
     * @param username the username
     * @return JWT token
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Extracts username from JWT token.
     *
     * @param token the JWT token
     * @return username
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Validates JWT token expiration.
     *
     * @param token the JWT token
     * @param user the user to validate against
     * @return true if valid, else false
     */
    public boolean isTokenValid(String token, User user) {
        final String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    /**
     *
     * Checks if the JWT token has expired
     *
     * @param token the JWT token
     * @return true if expired, else false
     */
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }


    /**
     * Get the expiration date from the token
     *
     * @param token the JWT token
     * @return expiration date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Helper method for extracting a specific claim from a token
     *
     *
     * @param token the JWT token
     * @param claimsResolver function that extracts a claim
     * @param <T> claim type
     * @return extracted claim
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Parses and extracts ALL claims from a JWT token
     * @param token the JWT token
     * @return all claims in the token
     * @throws io.jsonwebtoken if token is invalid
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
