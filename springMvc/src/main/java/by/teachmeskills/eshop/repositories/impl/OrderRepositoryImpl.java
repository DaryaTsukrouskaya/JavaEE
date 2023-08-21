package by.teachmeskills.eshop.repositories.impl;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.repositories.OrderRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private final static Logger log = LogManager.getLogger(CategoryRepositoryImpl.class);
    private static final String CREATE_ORDER_QUERY = "INSERT INTO orders(price,orderDate,userId,address)";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM orders WHERE id=?";
    private static final String GET_ALL_ORDERS_QUERY = "SELECT * FROM orders";
    private static final String GET_ORDER_QUERY = "SELECT * FROM orders WHERE id=?";
    private static final String GET_ORDER_BY_USER_ID_QUERY = "SELECT * FROM orders WHERE userId=?";

    @Override
    public void create(Order order) throws DBConnectionException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER_QUERY)) {
            statement.setBigDecimal(1, order.getPrice());
            statement.setTimestamp(2, Timestamp.valueOf(order.getOrderDate().atStartOfDay()));
            statement.setInt(3, order.getUserId());
            statement.setString(4, order.getAddress());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }

    }

    @Override
    public void delete(int id) throws DBConnectionException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_QUERY)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
    }

    @Override
    public List<Order> read() throws DBConnectionException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS_QUERY)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                orderList.add(Order.builder().id(set.getInt("id")).price(set.getBigDecimal("price"))
                        .orderDate(set.getTimestamp("birthDate").toLocalDateTime().toLocalDate()).userId(set.getInt("userId")).
                        address(set.getString("address")).build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return orderList;
    }

    @Override
    public Order findById(int id) throws DBConnectionException {
        Order order = null;
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDER_QUERY)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            order = Order.builder().id(set.getInt("id")).price(set.getBigDecimal("price"))
                    .orderDate(set.getTimestamp("orderDate").toLocalDateTime().toLocalDate()).userId(set.getInt("userId")).
                    address(set.getString("address")).build();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return order;
    }

    @Override
    public List<Order> findByUserId(int id) throws DBConnectionException {
        List<Order> orderList = null;
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDER_BY_USER_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            orderList.add(Order.builder().id(set.getInt("id")).price(set.getBigDecimal("price")).
                    orderDate(set.getTimestamp("orderDate").toLocalDateTime().toLocalDate()).
                    userId(set.getInt("userId")).address(set.getString("address")).build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }
}
