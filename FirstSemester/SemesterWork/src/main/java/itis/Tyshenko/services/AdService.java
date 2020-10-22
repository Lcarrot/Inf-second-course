package itis.Tyshenko.services;

import java.util.List;

public interface AdService<T> {
    List<T> getAllAd();
    void addAd(T entity);
    void deleteAd(T entity);
}
