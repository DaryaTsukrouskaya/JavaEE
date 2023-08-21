package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.exceptions.DBConnectionException;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {
    Order findById(int id) throws DBConnectionException;

    List<Order> findByUserId(int id) throws DBConnectionException;
}
