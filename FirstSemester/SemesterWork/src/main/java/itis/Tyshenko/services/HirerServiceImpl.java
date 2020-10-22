package itis.Tyshenko.services;

import itis.Tyshenko.repositories.HirerRepository;

public class HirerServiceImpl implements HirerService {

    HirerRepository repository;

    public HirerServiceImpl(HirerRepository repository) {
        this.repository = repository;
    }
}
