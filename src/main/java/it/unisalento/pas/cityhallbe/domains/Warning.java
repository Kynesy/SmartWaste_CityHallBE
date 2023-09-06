/**
 * Rappresenta una classe Warning che sarà mappata in un documento MongoDB.
 */
package it.unisalento.pas.cityhallbe.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Questa classe rappresenta un avviso nel sistema.
 */
@Document("warnings")
@NoArgsConstructor
@Getter
@Setter
public class Warning {
    /**
     * Identificatore univoco dell'avviso.
     */
    @Id private String id;

    /**
     * Identificatore dell'utente associato all'avviso.
     */
    private String userId;

    /**
     * Messaggio dell'avviso.
     */
    private String message;
}
