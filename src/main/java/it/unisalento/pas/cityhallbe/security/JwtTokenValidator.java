package it.unisalento.pas.cityhallbe.security;

import it.unisalento.pas.cityhallbe.configurations.SecurityConstants;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;

/**
 * Questa classe implementa un token validator per convalidare un oggetto Jwt.
 */
public class JwtTokenValidator implements OAuth2TokenValidator<Jwt> {
    /**
     * Valida un oggetto Jwt verificando la scadenza del token e il suo issuer.
     *
     * @param jwt L'oggetto Jwt da convalidare.
     * @return Un risultato di convalida OAuth2TokenValidatorResult che indica se il token è valido o meno.
     */
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

    /**
     * Verifica se il token è scaduto confrontando la data di scadenza con l'istante attuale.
     *
     * @param jwt L'oggetto Jwt da convalidare.
     * @return True se il token è scaduto, false altrimenti.
     */
    private boolean isTokenExpired(Jwt jwt) {
        Instant tokenExpiration = jwt.getExpiresAt();
        return tokenExpiration != null && tokenExpiration.isBefore(Instant.now());
    }

    /**
     * Verifica se l'issuer del token è valido confrontandolo con una lista di issuer attendibili.
     *
     * @param jwt L'oggetto Jwt da convalidare.
     * @return True se l'issuer è valido, false altrimenti.
     */
    private boolean isValidIssuer(Jwt jwt) {
        String tokenIssuer = String.valueOf(jwt.getIssuer());
        String[] expectedIssuer = SecurityConstants.ISSUER_LIST;
        for (String issuer : expectedIssuer) {
            if (issuer.equals(tokenIssuer)) {
                return true; // Trovato un issuer corrispondente nella lista
            }
        }

        return false; // Nessun issuer corrispondente trovato
    }
}
