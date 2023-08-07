package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.enums.State;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.UserService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import by.teachmeskills.shop.services.impl.UserServiceImpl;
import by.teachmeskills.shop.utils.ValidatorUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SignInCommand implements BaseCommand {
    private final Logger log = LogManager.getLogger(CategoryProductPageCommand.class);
    private static final CategoryService categoryService = new CategoryServiceImpl();
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        if (email != null && password != null && ValidatorUtils.emailValidation(email) == State.VALID) {
            try {
                req.setAttribute("categories", categoryService.read());
                User user = userService.findByEmail(email);
                if (user != null && user.getPassword().equals(password)) {
                    req.getSession().setAttribute("user", user);
                } else {
                    req.setAttribute("state", "Неверный логин или пароль");
                    return PagesPathEnum.SIGN_IN_PAGE.getPath();
                }
            } catch (DBConnectionException | ExecuteQueryException e) {
                log.error(e.getMessage());
                req.setAttribute("state", "Неверный логин или пароль");
                return PagesPathEnum.SIGN_IN_PAGE.getPath();
            }
        } else {
            return PagesPathEnum.SIGN_IN_PAGE.getPath();
        }
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}

