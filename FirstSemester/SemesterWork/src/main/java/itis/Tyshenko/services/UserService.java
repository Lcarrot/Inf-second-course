package itis.Tyshenko.services;

import java.util.List;

public interface UserService<T> {
    List<T> getAllUsers();
    void addUser(T entity);
    void deleteUser(T entity);
}
