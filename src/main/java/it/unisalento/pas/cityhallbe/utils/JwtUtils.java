package it.unisalento.pas.cityhallbe.utils;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${cityHallBE.app.jwtSecret}")
    private String jwtSecret;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token) throws JsonProcessingException {
        String[] chunks = token.split("\\.");
        Claims claims = getClaims(new String(Decoders.BASE64URL.decode(chunks[1])));
        return claims.get("sub", String.class);
    }

    public String getUserRoleFromJwtToken(String token) throws JsonProcessingException {
        String[] chunks = token.split("\\.");
        Claims claims = getClaims(new String(Decoders.BASE64URL.decode(chunks[1])));
        return claims.get("role", String.class);
    }

    public Claims getClaims(String payload) throws JsonProcessingException {
        Map<String, Object> mapping = new ObjectMapper().readValue(payload, HashMap.class);
        Claims claims = Jwts.claims(mapping);
        return claims;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            String[] chunks = authToken.split("\\.");

            if(chunks.length != 3) throw new MalformedJwtException(null);

            String header = new String(Decoders.BASE64URL.decode(chunks[0]));
            String payload = new String(Decoders.BASE64URL.decode(chunks[1]));
            String signature = chunks[2];

            System.out.println(payload);

            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    private boolean isSignatureValid(String tokenSignature, Key key){
        return true;
    }
}

