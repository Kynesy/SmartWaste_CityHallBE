package it.unisalento.pas.cityhallbe.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Questa classe implementa un converter per convertire un oggetto Jwt in un AbstractAuthenticationToken
 * utilizzato per autenticare l'utente in base alle informazioni contenute nel JWT.
 */
public class JwtTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final String CLAIM_ROLE = "role"; // La chiave per il claim del ruolo nel JWT

    /**
     * Converte un oggetto Jwt in un AbstractAuthenticationToken.
     *
     * @param jwt L'oggetto Jwt da convertire.
     * @return Un oggetto AbstractAuthenticationToken contenente le autorizzazioni dell'utente.
     */
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractRole(jwt.getClaims());

        return new JwtAuthenticationToken(jwt, authorities);
    }

    /**
     * Estrae il ruolo dall'oggetto Jwt.
     *
     * @param claims Le informazioni contenute nel JWT.
     * @return Una collezione di autorizzazioni (ruoli) associate all'utente.
     */
    private Collection<GrantedAuthority> extractRole(Map<String, Object> claims) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        if (claims.containsKey(CLAIM_ROLE)) {
            String role = (String) claims.get(CLAIM_ROLE);
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
}
