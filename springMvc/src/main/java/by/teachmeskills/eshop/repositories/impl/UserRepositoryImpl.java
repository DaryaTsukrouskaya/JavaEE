package by.teachmeskills.eshop.repositories.impl;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.ExecuteQueryException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.eshop.repositories.UserRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final static Logger log = LogManager.getLogger(UserRepositoryImpl.class);
    private final static String CREATE_USER_QUERY = "INSERT INTO users(name,surname,birthDate,email,password) VALUES(?,?,?,?,?)";
    private final static String GET_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    private final static String GET_USER_BY_EMAIL_AND_PASSWORD_QUERY = "SELECT * FROM users WHERE email=? AND password=?";
    private final static String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?";
    private final static String GET_ALL_USERS_QUERY = "SELECT * FROM users";
    private final static String UPDATE_USER_PASSWORD_QUERY = "UPDATE users SET password=? WHERE email=?";
    private final static String UPDATE_USER_EMAIL_QUERY = "UPDATE users SET email=? WHERE email=?";

    @Override
    public void create(User user) throws DBConnectionException, UserAlreadyExistsException {
        Connection connection = pool.getConnection();
        try {
            if (findByEmailAndPassword(user.getEmail(), user.getPassword()) != null) {
                throw new UserAlreadyExistsException("Такой пользователь уже существует!");
            }
        } catch (ExecuteQueryException e) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setTimestamp(3, Timestamp.valueOf(user.getBirthDate().atStartOfDay()));
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getPassword());
                statement.execute();
            } catch (SQLException ex) {
                log.error(ex.getMessage());
            } finally {
                pool.closeConnection(connection);
            }
        }
    }

    @Override
    public void delete(int id) throws DBConnectionException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
    }

    @Override
    public List<User> read() throws DBConnectionException {
        List<User> users = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(User.builder().id(resultSet.getInt("id")).name(resultSet.getString("name")).surname(resultSet.getString("surname")).
                        birthDate(resultSet.getTimestamp("birthDate").toLocalDateTime().toLocalDate()).email(resultSet.getString("email"))
                        .password(resultSet.getString("password")).build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return users;

    }

    @Override
    public User findById(int id) throws DBConnectionException, ExecuteQueryException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            User user = User.builder().id(resultSet.getInt("id")).name(resultSet.getString("name")).surname(resultSet.getString("surname")).
                    birthDate(resultSet.getTimestamp("birthDate").toLocalDateTime().toLocalDate()).email(resultSet.getString("email"))
                    .password(resultSet.getString("password")).build();
            return user;
        } catch (SQLException e) {
            throw new ExecuteQueryException("User not found!");
        } finally {
            pool.closeConnection(connection);
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws DBConnectionException, ExecuteQueryException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_EMAIL_AND_PASSWORD_QUERY)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            User user = User.builder().id(resultSet.getInt("id")).name(resultSet.getString("name")).surname(resultSet.getString("surname")).
                    birthDate(resultSet.getTimestamp("birthDate").toLocalDateTime().toLocalDate()).email(resultSet.getString("email"))
                    .password(resultSet.getString("password")).build();
            return user;
        } catch (SQLException e) {
            throw new ExecuteQueryException("User not found!");
        } finally {
            pool.closeConnection(connection);
        }
    }

    @Override
    public void updatePassword(String password, String email) throws DBConnectionException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD_QUERY)) {
            statement.setString(1, password);
            statement.setString(2, email);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public void updateEmail(String previousEmail, String newEmail) throws DBConnectionException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_EMAIL_QUERY)) {
            statement.setString(1, newEmail);
            statement.setString(2, previousEmail);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
