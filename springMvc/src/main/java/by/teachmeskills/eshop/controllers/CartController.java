package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.Cart;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.enums.PagesPathEnum;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.services.ProductService;
import by.teachmeskills.eshop.services.impl.ProductServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cart")
@SessionAttributes({"cart"})
public class CartController {
    private final ProductService productService = new ProductServiceImpl();
    private final Logger log = LogManager.getLogger(CartController.class);

    @GetMapping
    public ModelAndView openCartPage(@ModelAttribute("cart") Cart cart) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("cart", cart);
        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath(), modelMap);
    }

    @GetMapping("/add")
    public ModelAndView addProductToCart(@RequestParam("product_id") int id, @ModelAttribute("cart") Cart cart) {
        ModelMap modelMap = new ModelMap();
        try {
            Product product = productService.findById(id);
            cart.addProduct(product);
            modelMap.addAttribute("cart", cart);
            modelMap.addAttribute("product", product);
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        }
        return new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath(), modelMap);

    }

    @GetMapping("/delete")
    public ModelAndView deleteProductFromCart(@ModelAttribute("cart") Cart cart, @RequestParam("product_id") int id) {
        ModelMap modelMap = new ModelMap();
        cart.removeProduct(id);
        modelMap.addAttribute("cart", cart);
        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath(), modelMap);
    }

    @GetMapping("/clear")
    public ModelAndView clearCart(@ModelAttribute("cart") Cart cart) {
        cart.clear();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("cart", cart);
        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath(), modelMap);
    }

    @ModelAttribute("cart")
    public Cart setShoppingCart() {
        return new Cart();
    }
}
