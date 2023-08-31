package it.unisalento.pas.cityhallbe.services;

import it.unisalento.pas.cityhallbe.domains.User;
import it.unisalento.pas.cityhallbe.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int createUser(User user) {
        try {
            userRepository.save(user);
            return 1;  // Return success
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return failure
        }
    }

    @Override
    public int updateUser(User user) {
        try {
            userRepository.save(user);
            return 1;  // Return success
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return failure
        }
    }

    @Override
    public int deleteUser(String ID) {
        try {
            userRepository.deleteById(ID);
            return 1;  // Return success
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return failure
        }
    }

    @Override
    public int existUser(String email) {
        try {
            Optional<User> existingUser = userRepository.findByEmail(email);
            return existingUser.isPresent() ? 1 : 0;  // Return 1 if the user exists, 0 otherwise
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return failure
        }
    }

    @Override
    public User findByID(String ID) {
        try {
            return userRepository.findById(ID).orElse(null);  // Find user by ID from MongoDB
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of failure
        }
    }

    @Override
    public List<String> getAllId() {
        List<User> userList = userRepository.findAll();
        List<String> idList = new ArrayList<>();

        for (User user:
             userList) {
            idList.add(user.getId());
        }

        return idList;
    }
}
