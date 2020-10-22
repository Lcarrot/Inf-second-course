package itis.Tyshenko.repositories;

import itis.Tyshenko.entity.users.Hired;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class HiredRepositoryImpl implements HiredRepository{

    private DataSource dataSource;

    public HiredRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Hired entity) {
    }

    @Override
    public void update(Hired entity) {

    }

    @Override
    public void delete(Hired entity) {

    }

    @Override
    public Optional<Hired> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Hired> findAll() {
        return null;
    }

    @Override
    public Optional<Hired> findByEmail(String email) {
        return Optional.empty();
    }
}
