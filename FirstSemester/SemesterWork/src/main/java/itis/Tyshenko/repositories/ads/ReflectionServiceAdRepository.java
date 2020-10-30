package itis.Tyshenko.repositories.ads;

import itis.Tyshenko.entity.ServiceAd;
import itis.Tyshenko.repositories.ReflectionCrudRepository;
import itis.Tyshenko.repositories.RowMapper;
import itis.Tyshenko.repositories.SqlJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class ReflectionServiceAdRepository extends ReflectionCrudRepository<ServiceAd> implements ServiceAdRepository{
    private final String[] fieldNames = {"id", "first_name", "surname", "email", "login", "hashPassword", "country", "gender", "org"};
    private final SqlJdbcTemplate<ServiceAd> template;

    public ReflectionServiceAdRepository(DataSource source) {
        this.template = new SqlJdbcTemplate<>(source);
    }

    private final RowMapper<ServiceAd> rowMapper =
            mapRow -> {
                ServiceAd ad = new ServiceAd();
                Class<? extends ServiceAd> hiredClass =ServiceAd.class;
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
    private String SQL_INSERT = "insert into ad_Service(id, first_name, surname, email, login, hashPassword, country, gender, org)" +
            " values (?,?,?,?,?,?,?,?,?)";

    @Override
    public void save(ServiceAd entity) {
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
            " login = ?, hashPassword = ?, country = ?, gender = ?, org = ? from ad_Service where id = ?";

    @Override
    public void update(ServiceAd entity) {
        List<Object> args;
        try {
            args = getParameters(entity, fieldNames, entity.getId());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        template.queryForChange(SQL_UPDATE, args);
    }

    @Override
    public void delete(ServiceAd entity) {
        List<Object> params = new LinkedList<>();
        params.add(entity.getId());
        //language=SQL
        String SQL_DELETE = "delete from ad_Service where id = ?";
        template.queryForChange(SQL_DELETE, params);
    }

    @Override
    public List<ServiceAd> findAll() {
        //language=SQL
        String SQL_SELECT_ALL = "select * from ad_Service;";
        return template.queryForReceive(SQL_SELECT_ALL, rowMapper);
    }
}
