package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.shop.repositories.UserRepository;
import by.teachmeskills.shop.repositories.impl.UserRepositoryImpl;
import by.teachmeskills.shop.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public List<User> read() throws DBConnectionException {
        return userRepository.read();
    }

    @Override
    public void create(User user) throws DBConnectionException, UserAlreadyExistsException {
        userRepository.create(user);
    }

    @Override
    public void delete(int id) throws DBConnectionException {
        userRepository.delete(id);

    }

    @Override
    public User findById(int id) throws ExecuteQueryException, DBConnectionException {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) throws ExecuteQueryException, DBConnectionException {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updatePassword(String password, String email) throws DBConnectionException {
        userRepository.updatePassword(password, email);

    }

    @Override
    public void updateEmail(String previousEmail, String newEmail) throws DBConnectionException {
        userRepository.updateEmail(previousEmail, newEmail);

    }
}
