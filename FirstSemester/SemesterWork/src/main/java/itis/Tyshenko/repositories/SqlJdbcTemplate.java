package itis.Tyshenko.repositories;

import itis.Tyshenko.entity.users.Hirer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SqlJdbcTemplate<T> {
    DataSource dataSource;

    public SqlJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<T> queryForReceive(String sql, RowMapper<T> rowMapper, Object ... args) {
        List<T> result = new LinkedList<>();

        try (PreparedStatement statement = addParametersInStatement(sql, args);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next()) {
                T record = rowMapper.mapRow(resultSet);
                result.add(record);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }


    public void queryForChange(String sql, Object[] argv, Object ... args) {

        Object[] objects = concatArrays(argv, args);

        try (PreparedStatement statement = addParametersInStatement(sql, objects)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private PreparedStatement addParametersInStatement(String sql, Object[] args) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 1; i <= args.length; i++) {
            statement.setObject(i, args[i]);
        }
        return statement;
    }

    private Object[] concatArrays(Object[] argv, Object[] args) {
        Object[] objects = Arrays.copyOf(argv, argv.length + args.length);
        System.arraycopy(argv, 0, objects, argv.length, args.length);
        return objects;
    }
}
