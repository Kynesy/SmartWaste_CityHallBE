/**
 * Rappresenta un oggetto di trasferimento dati (DTO) per l'entità User.
 */
package it.unisalento.pas.cityhallbe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Questa classe rappresenta un oggetto di trasferimento dati (DTO) per l'entità User.
 */
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    /**
     * Identificatore univoco dell'utente.
     */
    private String id;

    /**
     * Nome dell'utente.
     */
    private String name;

    /**
     * Cognome dell'utente.
     */
    private String surname;

    /**
     * Indirizzo email dell'utente.
     */
    private String email;

    /**
     * Data di nascita dell'utente.
     */
    private String bdate;
}
