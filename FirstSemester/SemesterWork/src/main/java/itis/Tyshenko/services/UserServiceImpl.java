package itis.Tyshenko.services;

import itis.Tyshenko.dto.UserDTO;
import itis.Tyshenko.entity.User;
import itis.Tyshenko.repositories.users.UserRepository;
import itis.Tyshenko.statuses.SignInStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return userRepository.getByLogin(login);
    }
    @Override
    public boolean equalsRowPasswordWithUserPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getHashPassword());
    }

    @Override
    public User addUser(UserDTO entity) {
        User user = User.builder().id(null).login(entity.getLogin()).
                gender(entity.getGender().equals("male")).country(entity.getCountry())
                .email(entity.getEmail()).hashPassword(passwordEncoder.encode(entity.getPassword())).build();
        userRepository.save(user);
        return user;
    }
}
