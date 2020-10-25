package itis.Tyshenko.services;

import java.util.List;

public interface Service<T> {
    List<T> getAll();
    void add(T entity);
    void delete(T entity);
}
