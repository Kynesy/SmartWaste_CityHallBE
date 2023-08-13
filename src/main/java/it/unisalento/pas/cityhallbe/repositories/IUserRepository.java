package it.unisalento.pas.cityhallbe.repositories;

import it.unisalento.pas.cityhallbe.domains.User;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
