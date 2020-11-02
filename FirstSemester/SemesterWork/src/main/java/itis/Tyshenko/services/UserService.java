package itis.Tyshenko.services;
import itis.Tyshenko.dto.UserDTO;
import itis.Tyshenko.entity.User;

import java.util.Optional;


public interface UserService {

    Optional<UserDTO> getUserByLogin(String login);

    void addUser(UserDTO entity, String password);
    boolean equalsRowPasswordWithUserPassword(String userHashedPassword, String password);
    void updateUser(UserDTO entity, String password);
}
