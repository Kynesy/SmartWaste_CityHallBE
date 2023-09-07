package it.unisalento.pas.cityhallbe.configurations;

/**
 * Questa classe contiene costanti utilizzate nelle configurazioni di sicurezza.
 */
public class SecurityConstants {
    /**
     * Lista degli issuer (emittenti) attendibili per i token JWT.
     */
    public static final String[] ISSUER_LIST = {"https://smart-city-waste-management.eu.auth0.com/", "test"};

    /**
     * Ruolo utente.
     */
    public static final String USER_ROLE_ID = "USER";

    /**
     * Ruolo amministratore.
     */
    public static final String ADMIN_ROLE_ID = "ADMIN";
}
