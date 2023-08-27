package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Cart;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    Product findById(int id) throws DBConnectionException;

    ModelAndView getProductsByCategory(int id);

    ModelAndView findProductsByKeywords(String words);

    ModelAndView addProductToCart(int id, Cart cart);

    ModelAndView deleteProductFromCart(int id, Cart cart);

    ModelAndView findProductByIdForProductPage(int id);
}
