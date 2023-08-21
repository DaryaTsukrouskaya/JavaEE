package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.eshop.repositories.OrderRepository;
import by.teachmeskills.eshop.repositories.impl.OrderRepositoryImpl;
import by.teachmeskills.eshop.services.OrderService;

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
