package it.unisalento.pas.cityhallbe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  // Use @RestController instead of @Controller to simplify JSON responses
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/exist/{userID}")
    public ResponseEntity<Boolean> existUser(@PathVariable String userID) {
        // Perform the logic to check if the user with the given userID exists
        // Replace this with your actual logic to check user existence

        boolean userExists = /* Perform the check based on userID */ true; // Replace this

        return ResponseEntity.status(HttpStatus.OK).body(userExists);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody String user) {
        // Perform the logic to create the user based on the given User object
        // Replace this with your actual logic to create a user

        System.out.println("User created: " + user);

        // Return a response indicating success
        String jsonResponse = "{\"message\": \"User created successfully\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody String user) {
        // Perform the logic to update the user based on the given User object
        // Replace this with your actual logic to update a user

        System.out.println("User updated: " + user);

        // Return a response indicating success
        String jsonResponse = "{\"message\": \"User updated successfully\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<String> deleteUser(@PathVariable String userID) {
        // Perform the logic to delete the user with the given userID
        // Replace this with your actual logic to delete a user

        System.out.println("User with ID " + userID + " deleted");

        // Return a response indicating success
        String jsonResponse = "{\"message\": \"User deleted successfully\"}";
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }

}
