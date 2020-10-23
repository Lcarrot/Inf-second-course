package itis.Tyshenko.repositories;

import itis.Tyshenko.entity.users.Hired;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;

public class HiredRepositoryImpl extends HiredRepository{

    private final SqlJdbcTemplate<Hired> template;

    private final String[] fieldNames = {"id","first_name", "surname", "email", "login", "hashPassword", "country", "gender"};

    public HiredRepositoryImpl(DataSource dataSource) {
        this.template = new SqlJdbcTemplate<>(dataSource);
    }

    private final RowMapper<Hired> hirerRowMapper =
            mapRow -> {
                Hired hired = new Hired();
                Class<? extends Hired> hiredClass = hired.getClass();
                for (String fieldName: fieldNames) {
                    Field field = null;
                    try {
                        field = hiredClass.getField(fieldName);
                        field.setAccessible(true);
                        field.set(hired, mapRow.getObject(fieldName));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    }

                }
                return hired;
            };

    //language=SQL
    private final String SQL_INSERT = "insert into hirer(first_name, surname, email, login, hashPassword, country, gender, org)  values (?,?,?,?,?,?,?,?)";
    @Override
    public void save(Hired entity) {
        List<Object> args = null;
        try {
            args = getParameters(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        template.queryForChange(SQL_INSERT, args);
    }

    //language=SQL
    private final String SQL_UPDATE = "update hirer set 'first_name' = ?, surname = ?, email = ?, hashPassword = ?, country = ?, gender = ?, org = ? where id = ?";
    @Override
    public void update(Hired entity) {
        List<Object> params;
        try {
            params = getParameters(entity, entity.getId());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        template.queryForChange(SQL_UPDATE, params);
    }

    //language=SQL
    private final String SQL_DELETE = "delete from hirer where id = ?";
    @Override
    public void delete(Hired entity) {
        List<Object> params = new LinkedList<>();
        params.add(entity.getId());
        template.queryForChange(SQL_DELETE, params);
    }

    //language=SQL
    private final String SQL_SELECT_BY_ID = "select * from hirer where id = ?";
    @Override
    public Optional<Hired> findById(Long id) {
        List<Hired> users = template.queryForReceive(SQL_SELECT_BY_ID, hirerRowMapper, id);
        if (users.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.ofNullable(users.get(0));
        }
    }

    //language=SQL
    private final String SQL_SELECT_ALL = "select * from hirer";
    @Override
    public List<Hired> findAll() {
        return template.queryForReceive(SQL_SELECT_ALL, hirerRowMapper);
    }

    //language=SQL
    private final String SQL_SELECT_BY_EMAIL = "select * from hirer where email = ?";
    @Override
    public Optional<Hired> findByEmail(String email) {
        List<Hired> users = template.queryForReceive(SQL_SELECT_BY_ID, hirerRowMapper, email);
        if (users.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.ofNullable(users.get(0));
        }
    }
}
