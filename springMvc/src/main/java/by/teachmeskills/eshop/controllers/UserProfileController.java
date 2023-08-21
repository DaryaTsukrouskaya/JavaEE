package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.enums.PagesPathEnum;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.services.ProductService;
import by.teachmeskills.eshop.services.impl.ProductServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class UserProfileController {
    ProductService productService = new ProductServiceImpl();
    private final Logger log = LogManager.getLogger(UserProfileController.class);

    @GetMapping
    public ModelAndView getUserPage(@SessionAttribute("user") User user) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("user", user);
        List<Product> productList1 = new ArrayList<>();
        List<Product> productList2 = new ArrayList<>();
        try {
            productList1.add(productService.findById(1));
            productList1.add(productService.findById(2));
            productList1.add(productService.findById(3));
            productList1.add(productService.findById(4));
            productList2.add(productService.findById(3));
            productList2.add(productService.findById(1));
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        }

        Order order1 = new Order(1, LocalDate.now(), productList1);
        Order order2 = new Order(2, LocalDate.now(), productList2);
        List<Order> userOrders = new ArrayList<>();
        userOrders.add(order1);
        userOrders.add(order2);
        modelMap.addAttribute("userOrders", userOrders);
        return new ModelAndView(PagesPathEnum.USER_PROFILE_PAGE.getPath(), modelMap);
    }
}
