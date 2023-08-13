package by.teachmeskills.shop.repositories.impl;


import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.shop.repositories.ProductRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private final static Logger log = LogManager.getLogger(CategoryRepositoryImpl.class);
    private static final String CREATE_PRODUCT_QUERY = "INSERT INTO products(name,description,imagePath,categoryId,price) VALUES(?,?,?,?,?)";
    private static final String DELETE_PRODUCT_QUERY = "DELETE FROM products WHERE id=?";
    private static final String GET_ALL_PRODUCTS_QUERY = "SELECT * FROM products";
    private static final String GET_PRODUCT_QUERY = "SELECT * FROM products WHERE id=?";
    private static final String GET_CATEGORY_PRODUCTS_QUERY = "SELECT * FROM products WHERE categoryId=?";
    private static final String GET_PRODUCTS_BY_KEYWORDS = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ? ORDER BY name ASC";

    @Override
    public void create(Product product) throws DBConnectionException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PRODUCT_QUERY)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getImagePath());
            statement.setInt(4, product.getCategoryId());
            statement.setBigDecimal(5, product.getPrice());
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
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_QUERY)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }

    }

    @Override
    public List<Product> read() throws DBConnectionException {
        List<Product> productList = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCTS_QUERY)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                productList.add(Product.builder().id(set.getInt("id")).name(set.getString("name"))
                        .description(set.getString("description")).imagePath(set.getString("imagePath")).categoryId(set.getInt("categoryId")).
                        price(set.getBigDecimal("price")).build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return productList;
    }

    @Override
    public Product findById(int id) throws DBConnectionException {
        Product product = null;
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_QUERY)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            product = Product.builder().id(set.getInt("id")).name(set.getString("name")).description(set.getString("description"))
                    .imagePath(set.getString("imagePath")).categoryId(set.getInt("categoryId")).
                    price(set.getBigDecimal("price")).build();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return product;
    }

    @Override
    public List<Product> getProductsByCategory(int id) throws DBConnectionException {
        List<Product> productList = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_PRODUCTS_QUERY)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                productList.add(Product.builder().id(set.getInt("id")).name(set.getString("name"))
                        .description(set.getString("description"))
                        .imagePath(set.getString("imagePath")).categoryId(set.getInt("categoryId")).
                        price(set.getBigDecimal("price")).build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return productList;
    }

    @Override
    public List<Product> findProductsByKeywords(String words) throws DBConnectionException {
        List<Product> productList = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_BY_KEYWORDS)) {
            words.trim();
            words = "%" + words + "%";
            statement.setString(1, words);
            statement.setString(2, words);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                productList.add(Product.builder().id(set.getInt("id")).name(set.getString("name"))
                        .description(set.getString("description"))
                        .imagePath(set.getString("imagePath")).categoryId(set.getInt("categoryId")).
                        price(set.getBigDecimal("price")).build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return productList;
    }
}
