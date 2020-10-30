package itis.Tyshenko.repositories.ads;

import itis.Tyshenko.entity.WorkAd;
import itis.Tyshenko.repositories.ReflectionCrudRepository;
import itis.Tyshenko.repositories.RowMapper;
import itis.Tyshenko.repositories.SqlJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReflectionWorkAdRepository extends ReflectionCrudRepository<WorkAd> implements WorkAdRepository {
    private final String[] fieldNames = {"id", "first_name", "surname", "email", "login", "hashPassword", "country", "gender", "org"};
    private final SqlJdbcTemplate<WorkAd> template;

    public ReflectionWorkAdRepository(DataSource source) {
        this.template = new SqlJdbcTemplate<>(source);
    }

    private final RowMapper<WorkAd> rowMapper =
            mapRow -> {
                WorkAd ad = new WorkAd();
                Class<? extends WorkAd> hiredClass = WorkAd.class;
                for (String fieldName : fieldNames) {
                    Field field;
                    try {
                        field = hiredClass.getField(fieldName);
                        field.setAccessible(true);
                        field.set(ad, mapRow.getObject(fieldName));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    }

                }
                return ad;
            };

    //language=SQL
    private String SQL_INSERT = "insert into ad_work(id, first_name, surname, email, login, hashPassword, country, gender, org)" +
            " values (?,?,?,?,?,?,?,?,?)";

    @Override
    public void save(WorkAd entity) {
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
            " login = ?, hashPassword = ?, country = ?, gender = ?, org = ? from ad_work where id = ?";

    @Override
    public void update(WorkAd entity) {
        List<Object> args;
        try {
            args = getParameters(entity, fieldNames, entity.getId());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        template.queryForChange(SQL_UPDATE, args);
    }

    @Override
    public void delete(WorkAd entity) {
        List<Object> params = new LinkedList<>();
        params.add(entity.getId());
        //language=SQL
        String SQL_DELETE = "delete from ad_work where id = ?";
        template.queryForChange(SQL_DELETE, params);
    }

    @Override
    public List<WorkAd> findAll() {
        //language=SQL
        String SQL_SELECT_ALL = "select * from ad_work;";
        return template.queryForReceive(SQL_SELECT_ALL, rowMapper, null);
    }

    public List<WorkAd> findByParameters(String sql, Map<String, Object> entity) {
        Set<String> fieldsList = entity.keySet();
        //language=SQL
        String SQL_SELECT = "select * from ad_work where ";
        String sqlString = addParamAfterWhereToSqlRequest(SQL_SELECT, fieldsList);
        List<Object> args = getEntityParam(entity);
        return template.queryForReceive(sqlString, rowMapper, args);
    }
}
