package itis.Tyshenko.repositories;

import itis.Tyshenko.entity.users.Hirer;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;

public class HirerRepositoryImpl extends HirerRepository {

    private final SqlJdbcTemplate<Hirer> template;

    private final String[] fieldNames = {"id", "first_name", "surname", "email", "login", "hashPassword", "country", "gender", "org"};

    public HirerRepositoryImpl(DataSource dataSource) {
        this.template = new SqlJdbcTemplate<>(dataSource);
    }

    private final RowMapper<Hirer> hirerRowMapper =
            mapRow -> {
                Hirer hirer = new Hirer();
                Class<? extends Hirer> hirerClass = hirer.getClass();
                for (String fieldName : fieldNames) {
                    Field field = null;
                    try {
                        field = hirerClass.getField(fieldName);
                        field.setAccessible(true);
                        field.set(hirer, mapRow.getObject(fieldName));
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        throw new IllegalStateException(e);
                    }
                }
                return hirer;
            };

    //language=SQL
    private final String SQL_INSERT = "insert into hirer(first_name, surname, email, login, hashPassword, country, gender, org)  values (?,?,?,?,?,?,?,?)";

    @Override
    public void save(Hirer entity) {
        List<Object> args = null;
        try {
            args = getParameters(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        template.queryForChange(SQL_UPDATE, args);
    }

    //language=SQL
    private final String SQL_UPDATE = "update hirer set first_name = ?, surname = ?, email = ?, hashPassword = ?, country = ?, gender = ?, org = ? where id = ?";

    @Override
    public void update(Hirer entity) {
        List<Object> args = null;
        try {
            args = getParameters(entity, entity.getId());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        template.queryForChange(SQL_UPDATE, args);
    }

    //language=SQL
    private final String SQL_DELETE = "delete from hirer where id = ?";

    @Override
    public void delete(Hirer entity) {
        List<Object> args = new LinkedList<>();
        args.add(entity.getId());
        template.queryForChange(SQL_DELETE, args);
    }

    //language=SQL
    private final String SQL_SELECT_BY_ID = "select * from hirer where id = ?";

    @Override
    public Optional<Hirer> findById(Long id) {
        List<Hirer> users = template.queryForReceive(SQL_SELECT_BY_ID, hirerRowMapper, id);
        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(users.get(0));
        }
    }

    //language=SQL
    private final String SQL_SELECT_ALL = "select * from hirer";

    @Override
    public List<Hirer> findAll() {
        return template.queryForReceive(SQL_SELECT_ALL, hirerRowMapper);
    }

    //language=SQL
    private final String SQL_SELECT_BY_EMAIL = "select * from hirer where email = ?";

    @Override
    public Optional<Hirer> findByEmail(String email) {
        List<Hirer> users = template.queryForReceive(SQL_SELECT_BY_ID, hirerRowMapper, email);
        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(users.get(0));
        }
    }
}
