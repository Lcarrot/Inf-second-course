package ru.itis.tyshenko.converter;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.tyshenko.dto.UserDto;
import ru.itis.tyshenko.service.UserService;

@Converter
public class CustomConverter implements org.springframework.core.convert.converter.Converter<String, UserDto> {

    @Autowired
    private UserService userService;

    @Override
    public UserDto convert(@NotNull String s) {
        return userService.getByLogin(s).orElseThrow(IllegalStateException::new);
    }
}
