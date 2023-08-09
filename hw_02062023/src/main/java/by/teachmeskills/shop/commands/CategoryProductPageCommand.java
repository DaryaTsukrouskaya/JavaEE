package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CategoryProductPageCommand implements BaseCommand {
    private final static Logger log = LogManager.getLogger(CategoryProductPageCommand.class);
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        try {
            req.setAttribute("categoryName", req.getParameter("name"));
            req.setAttribute("categoryProducts", productService.getProductsByCategory(Integer.valueOf(req.getParameter("id"))));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return PagesPathEnum.CATEGORY_PAGE.getPath();
    }
}
