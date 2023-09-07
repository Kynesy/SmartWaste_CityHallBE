package it.unisalento.pas.cityhallbe.services;

import it.unisalento.pas.cityhallbe.domains.Warning;
import it.unisalento.pas.cityhallbe.repositories.IWarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe rappresenta un servizio per la gestione degli avvisi (Warnings).
 */
@Service
public class WarningService implements IWarningService {
    private final IWarningRepository warningRepository;

    @Autowired
    public WarningService(IWarningRepository warningRepository) {
        this.warningRepository = warningRepository;
    }

    /**
     * Crea un nuovo avviso (Warning) nel sistema.
     *
     * @param warning L'oggetto avviso da creare.
     * @return 0 se la creazione ha successo, 1 in caso di errore.
     */
    @Override
    public int createWarning(Warning warning) {
        try {
            warningRepository.save(warning);
            return 0; // Restituisce successo
        } catch (Exception e) {
            e.printStackTrace();
            return 1; // Restituisce errore
        }
    }

    /**
     * Elimina un avviso (Warning) esistente dal sistema dato il suo ID.
     *
     * @param warningID L'ID dell'avviso da eliminare.
     * @return 0 se l'eliminazione ha successo, 1 in caso di errore.
     */
    @Override
    public int deleteWarning(String warningID) {
        try {
            warningRepository.deleteById(warningID);
            return 0; // Restituisce successo
        } catch (Exception e) {
            e.printStackTrace();
            return 1; // Restituisce errore
        }
    }

    /**
     * Ottiene una lista di avvisi (Warnings) associati a un utente dato il suo ID.
     *
     * @param userId L'ID dell'utente di cui si vogliono ottenere gli avvisi.
     * @return Una lista di avvisi associati all'utente o null in caso di errore.
     */
    @Override
    public ArrayList<Warning> getAllByUser(String userId) {
        try {
            List<Warning> warningList = warningRepository.findAllByUserId(userId);
            return new ArrayList<>(warningList);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Restituisce null in caso di errore
        }
    }
}
