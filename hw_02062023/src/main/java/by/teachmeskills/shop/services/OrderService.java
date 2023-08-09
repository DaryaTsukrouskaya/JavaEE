package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.Order;
import by.teachmeskills.shop.exceptions.DBConnectionException;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    Order findById(int id) throws DBConnectionException;

    List<Order> findByUserId(int id) throws DBConnectionException;
}
