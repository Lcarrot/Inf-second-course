package ru.itis.tyshenko.service;

import ru.itis.tyshenko.dto.AdminDto;
import ru.itis.tyshenko.form.AdminForm;

import java.util.Optional;

public interface AdminService extends Service<AdminDto, AdminForm> {

    Optional<AdminDto> authenticate(AdminForm form);
}
