package it.unisalento.pas.cityhallbe.configurations;

import it.unisalento.pas.cityhallbe.security.JwtTokenConverter;
import it.unisalento.pas.cityhallbe.security.JwtTokenValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Questa classe configura la sicurezza dell'applicazione.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenConverter jwtTokenConverter = new JwtTokenConverter();

    /**
     * Configura la catena di filtri di sicurezza per l'applicazione.
     *
     * @param http L'oggetto HttpSecurity da configurare.
     * @return La catena di filtri di sicurezza configurata.
     * @throws Exception Eccezione in caso di errore nella configurazione.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });
        http.csrf(AbstractHttpConfigurer::disable);

        // Definizione delle autorizzazioni per le richieste HTTP
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET, "/api/user/exist/{userID}").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.POST, "/api/user/create").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.POST, "/api/user/update").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.DELETE, "/api/user/delete/{userID}").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.GET, "/api/user/get/{userID}").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.GET, "/api/user/id/all").hasAnyAuthority(SecurityConstants.ADMIN_ROLE_ID)

                        .requestMatchers(HttpMethod.POST, "/api/warning/create").hasAnyAuthority(SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.DELETE, "/api/warning/delete/{warningId}").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.GET, "/api/warning/get/user/{userId}").hasAnyAuthority(SecurityConstants.USER_ROLE_ID)

                        .anyRequest().denyAll())
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtTokenConverter)
                ))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    /**
     * Configura le opzioni per il supporto di CORS (Cross-Origin Resource Sharing).
     *
     * @return Un oggetto CorsConfigurationSource configurato per il supporto CORS.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Configura il decodificatore Jwt per convalidare i token JWT.
     *
     * @return Un JwtDecoder configurato per la convalida dei token JWT.
     */
    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(SecurityConstants.ISSUER_LIST[0]);
        OAuth2TokenValidator<Jwt> tokenValidator = new JwtTokenValidator();
        jwtDecoder.setJwtValidator(tokenValidator);
        return jwtDecoder;
    }
}
