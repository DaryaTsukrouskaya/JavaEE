package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.ExecuteQueryException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;
import org.springframework.web.servlet.ModelAndView;

public interface UserService extends BaseService<User> {
    ModelAndView registerUser(User user, String repPassword) throws DBConnectionException, UserAlreadyExistsException;

    User findById(int id) throws ExecuteQueryException, DBConnectionException;

    ModelAndView authenticate(String email);

    void updatePassword(String password,String email) throws DBConnectionException;

    void updateEmail(String previousEmail,String newEmail) throws DBConnectionException;
}
