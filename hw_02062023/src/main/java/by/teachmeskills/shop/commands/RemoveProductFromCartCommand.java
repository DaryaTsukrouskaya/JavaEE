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

public class RemoveProductFromCartCommand implements BaseCommand {
    private final static Logger log = LogManager.getLogger(CategoryProductPageCommand.class);
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        int productId = Integer.parseInt(req.getParameter("productId"));
        if (cart != null && !cart.getProducts().isEmpty()) {
            try {
                Product product = productService.findById(productId);
                cart.removeProduct(product.getId());
                session.setAttribute("cart", cart);
            } catch (DBConnectionException e) {
                log.info(e.getMessage());
            }
        }
        return PagesPathEnum.CART_PAGE.getPath();
    }
}
