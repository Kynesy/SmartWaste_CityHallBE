package it.unisalento.pas.cityhallbe.repositories;

import it.unisalento.pas.cityhallbe.domains.Warning;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IWarningRepository extends MongoRepository<Warning, String> {
    List<Warning> findAllByUserId(String userID);

}
