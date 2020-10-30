package itis.Tyshenko.services;
import itis.Tyshenko.dto.UserDTO;
import itis.Tyshenko.entity.User;
import itis.Tyshenko.statuses.SignInStatus;
import java.util.Map;
import java.util.Optional;


public interface UserService {

    Optional<User> getUserByLogin(String login);

    User addUser(UserDTO entity);
    boolean equalsRowPasswordWithUserPassword(User user, String password);
}
