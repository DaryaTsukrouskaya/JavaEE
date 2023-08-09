package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.Order;
import by.teachmeskills.shop.exceptions.DBConnectionException;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {
    Order findById(int id) throws DBConnectionException;

    List<Order> findByUserId(int id) throws DBConnectionException;
}
