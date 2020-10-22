package itis.Tyshenko.repositories;

import itis.Tyshenko.entity.users.Hirer;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class HirerRepositoryImpl implements HirerRepository {

    private DataSource dataSource;

    public HirerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Hirer entity) {

    }

    @Override
    public void update(Hirer entity) {

    }

    @Override
    public void delete(Hirer entity) {

    }

    @Override
    public Optional<Hirer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Hirer> findAll() {
        return null;
    }

    @Override
    public Optional<Hirer> findByEmail(String email) {
        return Optional.empty();
    }
}
