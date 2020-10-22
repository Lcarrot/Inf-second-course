package itis.Tyshenko.services;

import itis.Tyshenko.repositories.HiredRepository;

public class HiredServiceImpl implements  HiredService {

    private HiredRepository repository;

    public HiredServiceImpl(HiredRepository repository) {
        this.repository = repository;
    }
}
