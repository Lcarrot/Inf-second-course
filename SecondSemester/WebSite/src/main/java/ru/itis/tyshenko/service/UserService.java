package ru.itis.tyshenko.service;
import ru.itis.tyshenko.dto.UserDto;
import ru.itis.tyshenko.form.UserForm;

import java.util.List;
import java.util.Optional;


public interface UserService extends Service<UserDto, UserForm> {

    Optional<UserDto> getByLogin(String login);

    Optional<UserDto> add(UserForm entity);
    Optional<UserDto> getById(Long id);
    Optional<UserDto> update(UserForm before, UserForm now);
    Optional<UserDto> confirmRegistration(String code);
    List<UserDto> getAll(Integer page);
    Integer getMaxPage();
    Optional<UserDto> authenticate(UserForm adminForm);
}
