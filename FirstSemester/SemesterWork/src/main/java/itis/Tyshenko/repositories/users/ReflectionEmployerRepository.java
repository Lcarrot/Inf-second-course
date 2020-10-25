package itis.Tyshenko.repositories.users;

import itis.Tyshenko.entity.users.Employer;
import itis.Tyshenko.repositories.ReflectionCrudRepository;
import itis.Tyshenko.repositories.RowMapper;
import itis.Tyshenko.repositories.SqlJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;

public class ReflectionEmployerRepository extends ReflectionCrudRepository<Employer> implements EmployerRepository {

    private final String[] fieldNames = {"id", "first_name", "surname", "email", "login", "hashPassword", "country", "gender", "org"};
    private final SqlJdbcTemplate<Employer> template;

    public ReflectionEmployerRepository(DataSource source) {
        this.template = new SqlJdbcTemplate<>(source);
    }

    private final RowMapper<Employer> rowMapper =
            mapRow -> {
                Employer user = new Employer();
                Class<? extends Employer> hiredClass = Employer.class;
                for (String fieldName : fieldNames) {
                    Field field;
                    try {
                        field = hiredClass.getField(fieldName);
                        field.setAccessible(true);
                        field.set(user, mapRow.getObject(fieldName));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    }

                }
                return user;
            };

    //language=SQL
    private String SQL_INSERT = "insert into employer(id, first_name, surname, email, login, hashPassword, country, gender, org)" +
            " values (?,?,?,?,?,?,?,?,?)";

    @Override
    public void save(Employer entity) {
        List<Object> args;
        try {
            args = getParameters(entity, fieldNames);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        template.queryForChange(SQL_INSERT, args);
    }

    //language=SQL
    private String SQL_UPDATE = "update set id = ?, first_name = ?, surname = ?, email = ?," +
            " login = ?, hashPassword = ?, country = ?, gender = ?, org = ? from employer where id = ?";

    @Override
    public void update(Employer entity) {
        List<Object> args;
        try {
            args = getParameters(entity, fieldNames, entity.getId());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        template.queryForChange(SQL_UPDATE, args);
    }

    @Override
    public void delete(Employer entity) {
        List<Object> params = new LinkedList<>();
        params.add(entity.getId());
        //language=SQL
        String SQL_DELETE = "delete from employer where id = ?";
        template.queryForChange(SQL_DELETE, params);
    }

    @Override
    public List<Employer> findAll() {
        //language=SQL
        String SQL_SELECT_ALL = "select * from employer;";
        return template.queryForReceive(SQL_SELECT_ALL, rowMapper, null);
    }

    @Override
    public List<Employer> findByParameters(String sql, Map<String, Object> entity) {
        Set<String> fieldsList = entity.keySet();
        //language=SQL
        String SQL_SELECT = "select * from employer where ";
        String sqlString = addParamAfterWhereToSqlRequest(SQL_SELECT, fieldsList);
        List<Object> args = getEntityParam(entity);
        return template.queryForReceive(sqlString, rowMapper, args);
    }
}
