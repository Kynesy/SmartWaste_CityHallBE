package it.unisalento.pas.cityhallbe.services;

import it.unisalento.pas.cityhallbe.domains.User;

import java.util.List;

public interface IUserService {

    int createUser(User user);
    int updateUser(User user);
    int deleteUser(String ID);
    int existUser(String email);
    User findByID(String ID);
    List<String> getAllId();
}
