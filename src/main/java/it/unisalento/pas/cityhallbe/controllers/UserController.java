package it.unisalento.pas.cityhallbe.controllers;

import it.unisalento.pas.cityhallbe.domains.User;
import it.unisalento.pas.cityhallbe.dto.UserDTO;
import it.unisalento.pas.cityhallbe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Questa classe è un controller che gestisce le operazioni relative agli utenti.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Restituisce una risposta JSON indicando se un utente esiste o meno.
     *
     * @param userID L'ID dell'utente da verificare.
     * @return Una risposta JSON con lo stato dell'utente.
     */
    @GetMapping("/exist/{userID}")
    public ResponseEntity<Boolean> existUser(@PathVariable String userID) {
        if (userService.existUser(userID) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    /**
     * Crea un nuovo utente utilizzando i dati forniti come corpo della richiesta.
     *
     * @param userDTO I dati dell'utente da creare.
     * @return Una risposta JSON che indica se la creazione dell'utente è avvenuta con successo o meno.
     */
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        User user = fromUserDTOtoUser(userDTO);

        int result = userService.createUser(user);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"User created successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"User creation failed\"}");
        }
    }

    /**
     * Aggiorna un utente esistente con i dati forniti come corpo della richiesta.
     *
     * @param userDTO I nuovi dati dell'utente.
     * @return Una risposta JSON che indica se l'aggiornamento dell'utente è avvenuto con successo o meno.
     */
    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        User user = fromUserDTOtoUser(userDTO);
        int result = userService.updateUser(user);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"User updated successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"User update failed\"}");
        }
    }

    /**
     * Elimina un utente in base all'ID specificato nella richiesta.
     *
     * @param userID L'ID dell'utente da eliminare.
     * @return Una risposta JSON che indica se l'eliminazione dell'utente è avvenuta con successo o meno.
     */
    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<String> deleteUser(@PathVariable String userID) {
        int result = userService.deleteUser(userID);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"User deleted successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"User deletion failed\"}");
        }
    }

    /**
     * Ottiene i dati di un utente in base all'ID specificato nella richiesta.
     *
     * @param userID L'ID dell'utente da recuperare.
     * @return Una risposta JSON contenente i dati dell'utente o uno stato 404 se l'utente non è stato trovato.
     */
    @GetMapping("/get/{userID}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userID) {
        User user = userService.findByID(userID);

        if (user != null) {
            UserDTO userDTO = fromUserToUserDTO(user);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Ottiene una lista di tutti gli ID utente.
     *
     * @return Una risposta JSON contenente una lista di ID utente o uno stato 404 se la lista è vuota.
     */
    @GetMapping("/get/get/idList")
    public ResponseEntity<ArrayList<String>> getAllId() {
        return ResponseEntity.ok(new ArrayList<>(userService.getAllIdList()));
    }

    // MAPPING

    /**
     * Converte un oggetto UserDTO in un oggetto User.
     *
     * @param userDTO L'oggetto UserDTO da convertire.
     * @return Un oggetto User con i dati dal UserDTO.
     */
    private User fromUserDTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setBdate(userDTO.getBdate());

        return user;
    }

    /**
     * Converte un oggetto User in un oggetto UserDTO.
     *
     * @param user L'oggetto User da convertire.
     * @return Un oggetto UserDTO con i dati dal User.
     */
    private UserDTO fromUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId("");
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setBdate(user.getBdate());

        return userDTO;
    }
}
