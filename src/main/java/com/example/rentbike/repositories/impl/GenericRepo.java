package com.example.rentbike.repositories.impl;

import com.example.rentbike.repositories.IGenericRepo;
import com.example.rentbike.utils.Configs;
import com.example.rentbike.utils.mappers.IResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class GenericRepo<T> implements IGenericRepo<T> {

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Configs.DB_URL, Configs.DB_USERNAME, Configs.DB_PASSWORD);
            System.out.println("DB connected");
            return conn;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        return null;
    }

    @Override
    public List<T> query(String sql, IResultSetMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public Integer insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Integer id = null;
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(statement, parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int count = 0;
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public List<T> find(String tableName, Hashtable<String, Object> filterTable, IResultSetMapper<T> rowMapper) {

        StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName);
        List<Object> parameterList = new ArrayList<>();

        if (filterTable != null && !filterTable.isEmpty()) {
            sql.append(" WHERE ");
            Enumeration<String> keys = filterTable.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                Object value = filterTable.get(key);
                sql.append(key).append(" = ? ");
                parameterList.add(value);
            }
        }

        return query(sql.toString(), rowMapper, parameterList.toArray(Object[]::new));
    }

    @Override
    public T findOne(String sql, IResultSetMapper<T> rowMapper, Object... parameters) {
        List<T> tList = query(sql, rowMapper, parameters);
        return tList.isEmpty() ? null : tList.get(0);
    }

    @Override
    public T findOne(String tableName, Hashtable<String, Object> filterTable, IResultSetMapper<T> rowMapper) {
        List<T> tList = find(tableName, filterTable, rowMapper);
        return tList.isEmpty() ? null : tList.get(0);
    }


    @Override
    public List<T> findAll(String tableName, IResultSetMapper<T> rowMapper) {

        return find(tableName, null, rowMapper);
    }

    @Override
    public T findById(String tableName, int id, IResultSetMapper<T> rowMapper) {
        Hashtable<String, Object> paramTable = new Hashtable<>();
        paramTable.put("id", id);
        List<T> tList = find(tableName, paramTable, rowMapper);
        return tList.isEmpty() ? null : tList.get(0);
    }


    private void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setParameter(PreparedStatement statement, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Timestamp) {
                    statement.setTimestamp(index, (Timestamp) parameter);
                } else if (parameter instanceof Boolean) {
                    statement.setBoolean(index, (Boolean) parameter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
