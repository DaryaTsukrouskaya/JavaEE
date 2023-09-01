package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.controllers.CategoryController;
import by.teachmeskills.eshop.entities.Cart;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.enums.PagesPathEnum;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.eshop.repositories.ProductRepository;
import by.teachmeskills.eshop.repositories.impl.ProductRepositoryImpl;
import by.teachmeskills.eshop.services.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository = new ProductRepositoryImpl();
    private final Logger log = LogManager.getLogger(CategoryServiceImpl.class);

    @Override
    public List<Product> read() throws DBConnectionException {
        return productRepository.read();
    }

    @Override
    public void create(Product product) throws DBConnectionException, UserAlreadyExistsException {
        productRepository.create(product);

    }

    @Override
    public void delete(int id) throws DBConnectionException {
        productRepository.delete(id);
    }

    @Override
    public Product findById(int id) throws DBConnectionException {
        return productRepository.findById(id);
    }

    @Override
    public ModelAndView getProductsByCategory(int id) {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("categoryProducts", productRepository.getProductsByCategory(id));
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        }
        return new ModelAndView(PagesPathEnum.CATEGORY_PAGE.getPath(), modelMap);
    }

    @Override
    public ModelAndView findProductsByKeywords(String words) {
        ModelMap modelMap = new ModelMap();
        if (words != null) {
            try {
                List<Product> products = productRepository.findProductsByKeywords(words);
                modelMap.addAttribute("products", products);
            } catch (DBConnectionException e) {
                log.error(e.getMessage());
            }
        }
        return new ModelAndView(PagesPathEnum.SEARCH_PAGE.getPath(), modelMap);
    }

    @Override
    public ModelAndView addProductToCart(int productId, Cart cart) {
        ModelMap modelMap = new ModelMap();
        try {
            Product product = findById(productId);
            cart.addProduct(product);
            modelMap.addAttribute("cart", cart);
            modelMap.addAttribute("product", product);
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        }
        return new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath(), modelMap);
    }

    @Override
    public ModelAndView deleteProductFromCart(int productId, Cart cart) {
        ModelMap modelMap = new ModelMap();
        cart.removeProduct(productId);
        modelMap.addAttribute("cart", cart);
        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath(), modelMap);
    }

    @Override
    public ModelAndView findProductByIdForProductPage(int id) {
        ModelMap modelMap = new ModelMap();
        try {
            Product product = findById(id);
            modelMap.addAttribute("categoryName", product.getName());
            modelMap.addAttribute("product", product);
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        }
        return new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath(), modelMap);
    }
}
