package it.unisalento.pas.cityhallbe.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;

public class JwtTokenValidator implements OAuth2TokenValidator<Jwt> {
    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        if (isTokenExpired(jwt)) {
            return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token", "Token expired", null));
        }

        if (!isValidIssuer(jwt)) {
            return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token", "Invalid issuer", null));
        }

        return OAuth2TokenValidatorResult.success();
    }

    private boolean isTokenExpired(Jwt jwt) {
        Instant tokenExpiration = jwt.getExpiresAt();
        return tokenExpiration != null && tokenExpiration.isBefore(Instant.now());
    }

    private boolean isValidIssuer(Jwt jwt) {
        String tokenIssuer = String.valueOf(jwt.getIssuer());
        String expectedIssuer = "https://smart-city-waste-management.eu.auth0.com/";
        return expectedIssuer.equals(tokenIssuer);
    }
}
