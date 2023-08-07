package by.teachmeskills.shop.repositories.impl;

import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.shop.repositories.CategoryRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private final static Logger log = LogManager.getLogger(CategoryRepositoryImpl.class);
    private static final String CREATE_CATEGORY_QUERY = "INSERT INTO categories(name) VALUES(?)";
    private static final String DELETE_CATEGORY_QUERY = "DELETE FROM categories WHERE id=?";
    private static final String GET_ALL_CATEGORIES_QUERY = "SELECT * FROM categories";
    private static final String GET_CATEGORY_QUERY = "SELECT * FROM categories WHERE id=?";

    @Override
    public void create(Category category) throws DBConnectionException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_CATEGORY_QUERY)) {
            statement.setString(1, category.getName());
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
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY_QUERY)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
    }

    @Override
    public List<Category> read() throws DBConnectionException {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_CATEGORIES_QUERY)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                categoryList.add(Category.builder().id(set.getInt("id")).name(set.getString("name"))
                        .build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return categoryList;
    }


    @Override
    public Category findById(int id) throws DBConnectionException {
        Connection connection = pool.getConnection();
        Category category = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_QUERY)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            category = Category.builder().id(set.getInt("id")).name(set.getString("name")).build();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return category;
    }
}
