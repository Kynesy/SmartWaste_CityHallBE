package it.unisalento.pas.cityhallbe.services;

import it.unisalento.pas.cityhallbe.domains.User;
import it.unisalento.pas.cityhallbe.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Questa classe rappresenta un servizio per la gestione degli utenti.
 */
@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Crea un nuovo utente nel sistema.
     *
     * @param user L'oggetto utente da creare.
     * @return 1 se la creazione ha successo, -1 in caso di errore.
     */
    @Override
    public int createUser(User user) {
        try {
            userRepository.save(user);
            return 1;  // Restituisce successo
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Restituisce errore
        }
    }

    /**
     * Aggiorna un utente esistente nel sistema.
     *
     * @param user L'oggetto utente da aggiornare.
     * @return 1 se l'aggiornamento ha successo, -1 in caso di errore.
     */
    @Override
    public int updateUser(User user) {
        try {
            userRepository.save(user);
            return 1;  // Restituisce successo
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Restituisce errore
        }
    }

    /**
     * Elimina un utente esistente dal sistema dato il suo ID.
     *
     * @param ID L'ID dell'utente da eliminare.
     * @return 1 se l'eliminazione ha successo, -1 in caso di errore.
     */
    @Override
    public int deleteUser(String ID) {
        try {
            userRepository.deleteById(ID);
            return 1;  // Restituisce successo
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Restituisce errore
        }
    }

    /**
     * Verifica l'esistenza di un utente dato un indirizzo email.
     *
     * @param userId L'indirizzo email dell'utente da verificare.
     * @return 1 se l'utente esiste, 0 se non esiste, -1 in caso di errore.
     */
    @Override
    public int existUser(String userId) {
        try {
            Optional<User> existingUser = userRepository.findById(userId);
            return existingUser.isPresent() ? 1 : 0;  // Restituisce 1 se l'utente esiste, 0 altrimenti
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Restituisce errore
        }
    }

    /**
     * Trova un utente dato il suo ID.
     *
     * @param ID L'ID dell'utente da trovare.
     * @return L'utente trovato o null in caso di errore o mancata corrispondenza.
     */
    @Override
    public User findByID(String ID) {
        try {
            return userRepository.findById(ID).orElse(null);  // Trova l'utente per ID da MongoDB
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Restituisce null in caso di errore
        }
    }

    /**
     * Ottiene una lista di ID di tutti gli utenti nel sistema.
     *
     * @return Una lista di ID degli utenti nel sistema.
     */
    @Override
    public List<String> getAllIdList() {
        List<User> userList = userRepository.findAll();
        List<String> idList = new ArrayList<>();

        for (User user : userList) {
            idList.add(user.getId());
        }

        return idList;
    }
}
