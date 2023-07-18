package by.teachmeskills.shop.utils;

import by.teachmeskills.shop.ConnectionPool;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.model.Category;
import by.teachmeskills.shop.model.Product;
import by.teachmeskills.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    private CRUDUtils() {

    }

    private static Connection connection;

    static {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connection = connectionPool.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private final static String CREATE_USER_QUERY = "INSERT INTO users(name,surname,birthDate,email,password) VALUES(?,?,?,?,?)";
    private final static String GET_USER_QUERY = "SELECT * FROM users WHERE email=?";

    private final static String GET_CATEGORIES_QUERY = "SELECT * FROM categories";

    private final static String GET_CATEGORY_PRODUCTS_QUERY = "SELECT * FROM products WHERE category = ?";
    private final static String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id=?";

    public static void createUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setTimestamp(3, Timestamp.valueOf(user.getBirthDate().atStartOfDay()));
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static User getUser(String email) throws ExecuteQueryException {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_QUERY)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            User user = new User(resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getTimestamp("birthDate").toLocalDateTime().toLocalDate(),
                    resultSet.getString("email"), resultSet.getString("password"));
            return user;
        } catch (SQLException e) {
            throw new ExecuteQueryException("User not found!");
        }
    }

    public static List<Category> getCategories() throws ExecuteQueryException {
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORIES_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getString("name")));
            }
            return categories;
        } catch (SQLException e) {
            throw new ExecuteQueryException("Categories are not found!");
        }

    }

    public static List<Product> getCategoryProducts(String categoryName) throws ExecuteQueryException {
        List<Product> categoryProducts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_PRODUCTS_QUERY)) {
            preparedStatement.setString(1, categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoryProducts.add(new Product(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("imageName"), resultSet.getString("category"),
                        resultSet.getBigDecimal("price")));
            }
            return categoryProducts;
        } catch (SQLException e) {
            throw new ExecuteQueryException("Category products are not found!");
        }
    }

    public static Product getProductById(int id) throws ExecuteQueryException {
        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product = new Product(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("imageName"), resultSet.getString("category"),
                        resultSet.getBigDecimal("price"));
            }
            return product;

        } catch (SQLException e) {
            throw new ExecuteQueryException("Product not found!");
        }

    }

}
