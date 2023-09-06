/**
 * Rappresenta un oggetto di trasferimento dati (DTO) per l'entità Warning.
 */
package it.unisalento.pas.cityhallbe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Questa classe rappresenta un oggetto di trasferimento dati (DTO) per l'entità Warning.
 */
@NoArgsConstructor
@Getter
@Setter
public class WarningDTO {
    /**
     * Identificatore univoco dell'avviso.
     */
    private String id;

    /**
     * Identificatore dell'utente associato all'avviso.
     */
    private String userId;

    /**
     * Messaggio dell'avviso.
     */
    private String message;
}
