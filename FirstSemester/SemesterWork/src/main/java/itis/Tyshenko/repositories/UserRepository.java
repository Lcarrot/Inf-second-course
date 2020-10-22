package itis.Tyshenko.repositories;

import java.util.Optional;

public interface UserRepository<T> extends CrudRepository<T> {
    Optional<T> findById(Long id);
    Optional<T> findByEmail(String email);
}
