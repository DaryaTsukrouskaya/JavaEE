package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.model.User;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;

public class HomePageCommand implements BaseCommand {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        if (((User) req.getSession().getAttribute("user")).getName().equals("isEmpty")) {
            return PagesPathEnum.SIGN_IN_PAGE.getPath();
        }
        try {
            req.setAttribute("categories", CRUDUtils.getCategories());
        } catch (DBConnectionException | ExecuteQueryException e) {
            e.getMessage();
        }
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}
