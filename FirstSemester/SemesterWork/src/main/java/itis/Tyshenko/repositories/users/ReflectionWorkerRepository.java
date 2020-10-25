package itis.Tyshenko.repositories.users;

import itis.Tyshenko.entity.users.Worker;
import itis.Tyshenko.repositories.ReflectionCrudRepository;
import itis.Tyshenko.repositories.RowMapper;
import itis.Tyshenko.repositories.SqlJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReflectionWorkerRepository extends ReflectionCrudRepository<Worker> implements WorkerRepository {
    private final String[] fieldNames = {"id", "first_name", "surname", "email", "login", "hashPassword", "country", "gender", "org"};
    private final SqlJdbcTemplate<Worker> template;

    public ReflectionWorkerRepository(DataSource source) {
        this.template = new SqlJdbcTemplate<>(source);
    }

    private final RowMapper<Worker> rowMapper =
            mapRow -> {
                Worker user = new Worker();
                Class<? extends Worker> hiredClass = Worker.class;
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
    private String SQL_INSERT = "insert into worker(id, first_name, surname, email, login, hashPassword, country, gender, org)" +
            " values (?,?,?,?,?,?,?,?,?)";

    @Override
    public void save(Worker entity) {
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
            " login = ?, hashPassword = ?, country = ?, gender = ?, org = ? from worker where id = ?";

    @Override
    public void update(Worker entity) {
        List<Object> args;
        try {
            args = getParameters(entity, fieldNames, entity.getId());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        template.queryForChange(SQL_UPDATE, args);
    }

    @Override
    public void delete(Worker entity) {
        List<Object> params = new LinkedList<>();
        params.add(entity.getId());
        //language=SQL
        String SQL_DELETE = "delete from worker where id = ?";
        template.queryForChange(SQL_DELETE, params);
    }

    @Override
    public List<Worker> findAll() {
        //language=SQL
        String SQL_SELECT_ALL = "select * from worker;";
        return template.queryForReceive(SQL_SELECT_ALL, rowMapper, null);
    }

    @Override
    public List<Worker> findByParameters(String sql, Map<String, Object> entity) {
        Set<String> fieldsList = entity.keySet();
        //language=SQL
        String SQL_SELECT = "select * from worker where ";
        String sqlString = addParamAfterWhereToSqlRequest(SQL_SELECT, fieldsList);
        List<Object> args = getEntityParam(entity);
        return template.queryForReceive(sqlString, rowMapper, args);
    }
}
