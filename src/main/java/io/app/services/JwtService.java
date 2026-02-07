package io.app.services;

import io.app.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="3zEYxnei5RPxxCVdg57NTtMbdXk6bnFnOKiw1taxdUC06fRdQybzqGQkV1Yvc8DROAqNOyTQDwKse09PX+gQag==";
    private final SecurityExpressionHandler securityExpressionHandler;

    public JwtService(SecurityExpressionHandler securityExpressionHandler) {
        this.securityExpressionHandler = securityExpressionHandler;
    }

    public String extractUsername(String token){
        return extractClaims(token,Claims::getSubject);
    }

    public boolean isTokenValid(UserDetails userDetails,String token){
        String username=extractUsername(token);
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return new Date().after(extractExpiration(token));
    }

    private Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims,T> resolver){
        Claims claims=extractClaims(token);
        return resolver.apply(claims);
    }

    public long getUserId(String token){
        return extractClaims(token).get("userId",Long.class);
    }

    public List<SimpleGrantedAuthority> getAuthorities(String token){
        String role=extractClaims(token).get("role",String.class);
        return List.of(
                new SimpleGrantedAuthority(role)
        );
    }

    private String getPassword(String token){
        return extractClaims(token).get("pass",String.class);
    }

    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(User user){
        Map<String,Object> claims=new HashMap<>();
        claims.put("userId",user.getId());
        claims.put("role",user.getRole().name());
        claims.put("pass",user.getPassword());
        return generateToken(user,claims);
    }

    private String generateToken(UserDetails userDetails, Map<String, Object> extraClaims){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365 * 5))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey(){
        byte[] key= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

}
