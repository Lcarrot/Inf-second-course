package ru.itis.tyshenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.itis.tyshenko.dto.UserDto;
import ru.itis.tyshenko.form.UserForm;
import ru.itis.tyshenko.util.mail.generator.MailsGenerator;

import java.util.Collections;
import java.util.Optional;
import java.util.List;

@Service
@Profile("test")
public class TestUserService implements UserService{

    @Autowired
    private MailsGenerator generator;

    @Value(value = "${spring.mail.username}")
    private String userName;

    @Value(value = "${server.url}")
    private String serverUrl;

    private final UserDto userDto = UserDto.builder().login("leo")
            .country("Russia").gender("helicopter").build();

    @Override
    public Optional<UserDto> getByLogin(String login) {
        userDto.setLogin(login);
        return Optional.of(userDto);
    }

    @Override
    public Optional<UserDto> add(UserForm entity) {
        System.out.println(generator.generateConfirmEmail(serverUrl, "kaka"));
        return Optional.of(userDto);
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        return Optional.of(userDto);
    }

    @Override
    public Optional<UserDto> update(UserForm before, UserForm now) {
        System.out.println("user was saved");
        return Optional.of(userDto);
    }

    @Override
    public Optional<UserDto> confirmRegistration(String code) {
        return Optional.of(userDto);
    }

    @Override
    public List<UserDto> getAll(Integer page) {
        return Collections.singletonList(userDto);
    }

    @Override
    public Integer getMaxPage() {
        return null;
    }
}
