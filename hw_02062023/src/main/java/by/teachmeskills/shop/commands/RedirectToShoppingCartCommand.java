package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.model.Cart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class RedirectToShoppingCartCommand implements BaseCommand {

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getProducts().isEmpty()) {
            req.setAttribute("cartProductsList", "");
        }
        req.setAttribute("cartProductsList", cart.getProducts());

        return PagesPathEnum.CART_PAGE.getPath();
    }
}
