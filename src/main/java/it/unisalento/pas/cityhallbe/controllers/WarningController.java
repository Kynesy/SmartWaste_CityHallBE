package it.unisalento.pas.cityhallbe.controllers;

import it.unisalento.pas.cityhallbe.domains.Warning;
import it.unisalento.pas.cityhallbe.dto.WarningDTO;
import it.unisalento.pas.cityhallbe.services.IWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Questa classe è un controller che gestisce le operazioni relative agli avvisi (warnings).
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/warning")
public class WarningController {
    private final IWarningService warningService;

    @Autowired
    public WarningController(IWarningService warningService) {
        this.warningService = warningService;
    }

    /**
     * Crea un nuovo avviso (warning) utilizzando i dati forniti come corpo della richiesta.
     *
     * @param warningDTO I dati dell'avviso da creare.
     * @return Una risposta JSON che indica se la creazione dell'avviso è avvenuta con successo o meno.
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createWarning(@RequestBody WarningDTO warningDTO) {
        Warning warning = fromWarningDTOtoWarning(warningDTO);

        if (warningService.createWarning(warning) == 0) {
            return ResponseEntity.ok("{\"message\": \"Warning created\"}");
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Elimina un avviso (warning) in base all'ID specificato nella richiesta.
     *
     * @param warningId L'ID dell'avviso da eliminare.
     * @return Una risposta JSON che indica se l'eliminazione dell'avviso è avvenuta con successo o meno.
     */
    @DeleteMapping("/delete/{warningId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteWarning(@PathVariable String warningId) {
        if (warningService.deleteWarning(warningId) == 0) {
            return ResponseEntity.ok("{\"message\": \"Warning deleted\"}");
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Ottiene tutti gli avvisi (warnings) associati a un utente specifico in base all'ID utente.
     *
     * @param userId L'ID dell'utente di cui si desiderano gli avvisi.
     * @return Una risposta JSON contenente una lista di avvisi (warnings) o uno stato 500 se si verifica un errore.
     */
    @GetMapping("/get/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ArrayList<WarningDTO>> getAllUserWarning(@PathVariable String userId) {
        ArrayList<WarningDTO> warningListDTO = new ArrayList<>();
        ArrayList<Warning> warningList = warningService.getAllByUser(userId);

        if (warningList != null) {
            for (Warning warn : warningList) {
                WarningDTO warnDTO = fromWarningToWarningDTO(warn);
                warningListDTO.add(warnDTO);
            }
            return ResponseEntity.ok(warningListDTO);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Converte un oggetto Warning in un oggetto WarningDTO.
     *
     * @param warn L'oggetto Warning da convertire.
     * @return Un oggetto WarningDTO con i dati dal Warning.
     */
    private WarningDTO fromWarningToWarningDTO(Warning warn) {
        WarningDTO warningDTO = new WarningDTO();

        warningDTO.setId(warn.getId());
        warningDTO.setUserId(warn.getUserId());
        warningDTO.setMessage(warn.getMessage());

        return warningDTO;
    }

    /**
     * Converte un oggetto WarningDTO in un oggetto Warning.
     *
     * @param warningDTO L'oggetto WarningDTO da convertire.
     * @return Un oggetto Warning con i dati dal WarningDTO.
     */
    private Warning fromWarningDTOtoWarning(WarningDTO warningDTO) {
        Warning warning = new Warning();

        warning.setId(warningDTO.getId());
        warning.setUserId(warningDTO.getUserId());
        warning.setMessage(warningDTO.getMessage());

        return warning;
    }
}
