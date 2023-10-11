package com.example.rentbike.repositories.impl;

import com.example.rentbike.models.order.Order;
import com.example.rentbike.utils.mappers.impl.OrderMapper;

public class OrderRepo extends GenericRepo<Order> {
    public Integer createNewOrder(Order order) {
        return insert("INSERT INTO orders(deposit, bikeId, start) VALUES (?, ?, ? )", new OrderMapper(), order.getDeposit(), order.getBike().getId(), order.getStart());
    }

    public void updateOrder(Order order) {
        update("update orders set end = ?, totalUpToNow = ? where orderId = ?", new OrderMapper(),order.getEnd(), order.getTotalUpToNow(), order.getId());
    }
}
