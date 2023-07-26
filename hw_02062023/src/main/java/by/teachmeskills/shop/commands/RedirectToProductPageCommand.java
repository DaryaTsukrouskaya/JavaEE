package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RedirectToProductPageCommand implements BaseCommand {
    private final static Logger log = LogManager.getLogger(CategoryProductPageCommand.class);

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        try {
            req.setAttribute("product", CRUDUtils.getProductById(Integer.parseInt(req.getParameter("id"))));
            req.setAttribute("productName", CRUDUtils.getProductById(Integer.parseInt(req.getParameter("id"))).getName());
        } catch (DBConnectionException | ExecuteQueryException e) {
            log.error(e.getMessage());
        }
        return PagesPathEnum.PRODUCT_PAGE.getPath();
    }
}
