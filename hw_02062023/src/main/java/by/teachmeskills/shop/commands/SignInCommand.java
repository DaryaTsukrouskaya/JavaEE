package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.enums.State;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.model.User;
import by.teachmeskills.shop.utils.CRUDUtils;
import by.teachmeskills.shop.utils.ValidatorUtils;
import jakarta.servlet.http.HttpServletRequest;

public class SignInCommand implements BaseCommand {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        if (email != null && password != null && ValidatorUtils.emailValidation(email) == State.VALID) {
            try {
                req.setAttribute("categories", CRUDUtils.getCategories());
                User user = CRUDUtils.getUser(email);
                if (user != null && user.getPassword().equals(password)) {
                    req.getSession().setAttribute("user", user);
                } else {
                    req.setAttribute("state", "Неверный логин или пароль");
                    return PagesPathEnum.SIGN_IN_PAGE.getPath();
                }
            } catch (DBConnectionException | ExecuteQueryException e) {
                System.out.println(e.getMessage());
                req.setAttribute("state", "Неверный логин или пароль");
                return PagesPathEnum.SIGN_IN_PAGE.getPath();
            }
        } else {
            return PagesPathEnum.SIGN_IN_PAGE.getPath();
        }
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}

