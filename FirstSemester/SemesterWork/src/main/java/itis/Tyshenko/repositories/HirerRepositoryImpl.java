package itis.Tyshenko.repositories;

import itis.Tyshenko.entity.users.Hirer;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class HirerRepositoryImpl implements HirerRepository {

    private final SqlJdbcTemplate<Hirer> template;

    public HirerRepositoryImpl(DataSource dataSource) {
        this.template = new SqlJdbcTemplate<>(dataSource);
    }

    private final RowMapper<Hirer> hirerRowMapper = mapRow -> Hirer.builder().
            id(mapRow.getLong("id")).
            name(mapRow.getString("name")).surname(mapRow.getString("surname")).
            email(mapRow.getString("email")).
            login(mapRow.getString("login")).hashPassword(mapRow.getString("hashPassword"))
            .country(mapRow.getString("country")).gender(mapRow.getBoolean("gender"))
            .organization(mapRow.getString("org")).build();

    //language=SQL
    private final String SQL_INSERT = "insert into hirer(first_name, surname, email, login, hashPassword, country, gender, org)  values (?,?,?,?,?,?,?,?)";
    @Override
    public void save(Hirer entity) {
        Object[] args = getUserFields(entity);
        template.queryForChange(SQL_INSERT, args);
    }

    //language=SQL
    private final String SQL_UPDATE = "update hirer set 'first_name' = ?, surname = ?, email = ?, hashPassword = ?, country = ?, gender = ?, org = ? where id = ?";
    @Override
    public void update(Hirer entity) {
        Object[] args = getUserFields(entity);
        template.queryForChange(SQL_UPDATE, args, entity.getId());
    }

    //language=SQL
    private final String SQL_DELETE = "delete from hirer where id = ?";
    @Override
    public void delete(Hirer entity) {
        template.queryForChange(SQL_DELETE, null, entity.getId());
    }

    //language=SQL
    private final String SQL_SELECT_BY_ID = "select * from hirer where id = ?";
    @Override
    public Optional<Hirer> findById(Long id) {
        List<Hirer> users = template.queryForReceive(SQL_SELECT_BY_ID, hirerRowMapper, id);
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
        }
        else {
            return Optional.ofNullable(users.get(0));
        }
    }

    private Object[] getUserFields(Hirer hirer) {
        return new Object[]{hirer.getName(), hirer.getSurname(), hirer.getEmail(), hirer.getLogin(),
                hirer.getHashPassword(), hirer.getCountry(), hirer.getCountry(),
                hirer.isGender(), hirer.getOrganization()};
    }
}
