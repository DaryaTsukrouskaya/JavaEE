package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

public class CategoryProductPageCommand implements BaseCommand {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        try {
            req.setAttribute("categoryName", req.getParameter("name"));
            req.setAttribute("categoryProducts", CRUDUtils.getCategoryProducts(req.getParameter("name")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return PagesPathEnum.CATEGORY_PAGE.getPath();
    }
}
