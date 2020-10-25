package itis.Tyshenko.repositories.users;

import itis.Tyshenko.entity.users.User;
import itis.Tyshenko.repositories.CrudRepository;

public interface UserRepository<T extends User> extends CrudRepository<T> {
}
