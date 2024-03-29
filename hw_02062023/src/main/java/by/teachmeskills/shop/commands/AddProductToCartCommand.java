package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.entities.Cart;
import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AddProductToCartCommand implements BaseCommand {
    private final static Logger log = LogManager.getLogger(AddProductToCartCommand.class);
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        int productId = Integer.parseInt(req.getParameter("productId"));
        try {
            Product product = productService.findById(productId);
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            cart.addProduct(product);
            session.setAttribute("cart", cart);
            req.setAttribute("product", product);
            log.info("product added");
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        }
        return PagesPathEnum.PRODUCT_PAGE.getPath();
    }
}
