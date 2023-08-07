package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.entities.Order;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.shop.repositories.OrderRepository;
import by.teachmeskills.shop.repositories.impl.OrderRepositoryImpl;
import by.teachmeskills.shop.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public List<Order> read() throws DBConnectionException {
        return orderRepository.read();
    }

    @Override
    public void create(Order order) throws DBConnectionException, UserAlreadyExistsException {
        orderRepository.create(order);
    }

    @Override
    public void delete(int id) throws DBConnectionException {
        orderRepository.delete(id);
    }

    @Override
    public Order findById(int id) throws DBConnectionException {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByUserId(int id) throws DBConnectionException {
        return orderRepository.findByUserId(id);
    }
}
