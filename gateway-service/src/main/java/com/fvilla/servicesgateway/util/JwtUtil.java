package com.fvilla.servicesgateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    public static final String SECRET_KEY = "614E645267556A586E3272357538782F413F4428472B4B6250655368566D5971";

    private Key getSignInKey() {
        byte[] keyBites = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBites);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiraton(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiraton(token).before(new Date());
    }

    public boolean validateToken(final String token) {
        return !isTokenExpired(token);
    }

}
