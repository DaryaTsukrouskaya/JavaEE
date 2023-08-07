package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class HomePageCommand implements BaseCommand {
    private final static Logger log = LogManager.getLogger(CategoryProductPageCommand.class);
    private static final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        if (((User) req.getSession().getAttribute("user")).getName().equals("isEmpty")) {
            return PagesPathEnum.SIGN_IN_PAGE.getPath();
        }
        try {
            req.setAttribute("categories", categoryService.read());
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        }
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}
