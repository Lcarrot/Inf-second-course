package itis.Tyshenko.repositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqlJdbcTemplate<T> {
    DataSource dataSource;

    public SqlJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<T> queryForReceive(String sql, RowMapper<T> rowMapper, List<Object> arguments) {
        List<T> result = new LinkedList<>();
        try (PreparedStatement statement = addParametersInStatement(sql, arguments);
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

    public void queryForChange(String sql, List<Object> args) {

        try (PreparedStatement statement = addParametersInStatement(sql, args)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private PreparedStatement addParametersInStatement(String sql, List<Object> args) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        if (args != null) {
            int count = args.size();
            for (int i = 1; i <= count; i++) {
                statement.setObject(i, args.get(i));
            }
        }

        return statement;
    }
}
