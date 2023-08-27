package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.ExecuteQueryException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;
import org.springframework.web.servlet.ModelAndView;

public interface UserService extends BaseService<User> {
    ModelAndView registerUser(User user, String repPassword);

    User findById(int id) throws ExecuteQueryException, DBConnectionException;

    ModelAndView authenticate(String email, String password);

    void updatePassword(String password, String email) throws DBConnectionException;

    void updateEmail(String previousEmail, String newEmail) throws DBConnectionException;

    ModelAndView userServicePage(User user);

}
