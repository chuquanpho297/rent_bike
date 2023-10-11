package com.example.rentbike.utils.mappers.impl;

import com.example.rentbike.models.order.Order;
import com.example.rentbike.utils.mappers.IResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements IResultSetMapper<Order> {
    @Override
    public Order mapRow(ResultSet res) throws SQLException {
        Order order = new Order();
        order.setId(res.getInt("orderId"));
        order.setStart(res.getTimestamp("start"));
        order.setEnd(res.getTimestamp("end"));
        order.setDeposit(res.getInt("deposit"));
        order.setTotalUpToNow(res.getInt("totalUpToNow"));
        return order;
    }
}
