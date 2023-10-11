package com.example.rentbike.repositories;

import com.example.rentbike.utils.mappers.IResultSetMapper;

import java.util.Hashtable;
import java.util.List;

public interface IGenericRepo<T> {
    List<T> query(String sql, IResultSetMapper<T> rowMapper, Object... parameters);
    void update (String sql, Object... parameters);
    Integer insert (String sql, Object... parameters);
    int count(String sql, Object... parameters);
    List<T> find(String tableName, Hashtable<String, Object> filterTable, IResultSetMapper<T> rowMapper);
    T findOne(String sql,IResultSetMapper<T> rowMapper, Object... parameters);
    T findOne(String tableName, Hashtable<String, Object> filterTable, IResultSetMapper<T> rowMapper);
    List<T> findAll(String tableName, IResultSetMapper<T> rowMapper);
    T findById(String tableName, int id,IResultSetMapper<T> rowMapper);
}
