package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.model.Order;
import by.teachmeskills.shop.model.Product;
import by.teachmeskills.shop.model.User;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserProfileCommand implements BaseCommand {
    private final static Logger log = LogManager.getLogger(CategoryProductPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        List<Product> productList1 = new ArrayList<>();
        List<Product> productList2 = new ArrayList<>();
        try {
            productList1.add(CRUDUtils.getProductById(1));
            productList1.add(CRUDUtils.getProductById(2));
            productList1.add(CRUDUtils.getProductById(3));
            productList1.add(CRUDUtils.getProductById(4));
            productList2.add(CRUDUtils.getProductById(3));
            productList2.add(CRUDUtils.getProductById(1));
        } catch (DBConnectionException | ExecuteQueryException e) {
            log.error(e.getMessage());
        }

        Order order1 = new Order(1, LocalDate.now(), productList1);
        Order order2 = new Order(2, LocalDate.now(), productList2);
        List<Order> userOrders = new ArrayList<>();
        userOrders.add(order1);
        userOrders.add(order2);
        request.setAttribute("userOrders", userOrders);
        return PagesPathEnum.USER_PROFILE_PAGE.getPath();
    }
}
