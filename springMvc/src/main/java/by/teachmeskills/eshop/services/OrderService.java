package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.exceptions.DBConnectionException;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    Order findById(int id) throws DBConnectionException;

    List<Order> findByUserId(int id) throws DBConnectionException;
}
