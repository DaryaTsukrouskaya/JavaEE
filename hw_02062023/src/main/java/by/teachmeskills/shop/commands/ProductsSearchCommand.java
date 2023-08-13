package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductsSearchCommand implements BaseCommand {
    private final static Logger log = LogManager.getLogger(CategoryProductPageCommand.class);
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String keyWords = request.getParameter("keyWords");
        if (keyWords != null) {
            try {
                List<Product> products = productService.findProductsByKeywords(keyWords);
                request.setAttribute("products", products);
            } catch (DBConnectionException e) {
                log.error(e.getMessage());
            }
        }
        return PagesPathEnum.SEARCH_PAGE.getPath();
    }
}
