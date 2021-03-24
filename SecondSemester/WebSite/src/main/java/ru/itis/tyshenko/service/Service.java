package ru.itis.tyshenko.service;

import ru.itis.tyshenko.dto.Dto;
import ru.itis.tyshenko.form.Form;

import java.util.Optional;

public interface Service<T extends Dto, M extends Form> {

    Optional<T> add(M form);
}
