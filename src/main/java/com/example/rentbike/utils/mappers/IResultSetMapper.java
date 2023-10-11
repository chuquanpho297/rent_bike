package com.example.rentbike.utils.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetMapper<T> {
    T mapRow(ResultSet res) throws SQLException;
}
