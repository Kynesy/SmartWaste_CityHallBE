/**
 * Rappresenta una classe User che sarà mappata in un documento MongoDB.
 */
package it.unisalento.pas.cityhallbe.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Questa classe rappresenta un utente nel sistema.
 */
@Document("users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    /**
     * Identificatore univoco dell'utente.
     */
    @Id private String id;

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
