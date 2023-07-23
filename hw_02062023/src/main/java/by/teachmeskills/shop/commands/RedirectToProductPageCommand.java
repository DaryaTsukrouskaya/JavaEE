package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

public class RedirectToProductPageCommand implements BaseCommand {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        try {
            req.setAttribute("product", CRUDUtils.getProductById(Integer.parseInt(req.getParameter("id"))));
            req.setAttribute("productName", CRUDUtils.getProductById(Integer.parseInt(req.getParameter("id"))).getName());
        } catch (DBConnectionException | ExecuteQueryException e) {
            System.out.println(e.getMessage());
        }
        return PagesPathEnum.PRODUCT_PAGE.getPath();
    }
}
