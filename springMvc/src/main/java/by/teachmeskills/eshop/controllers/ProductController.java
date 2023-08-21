package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.enums.PagesPathEnum;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.services.ProductService;
import by.teachmeskills.eshop.services.impl.ProductServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService = new ProductServiceImpl();
    private final Logger log = LogManager.getLogger(ProductController.class);

    @GetMapping("/{id}")
    public ModelAndView getProductPage(@PathVariable int id) {
        ModelMap modelMap = new ModelMap();
        try {
            Product product = productService.findById(id);
            modelMap.addAttribute("categoryName", product.getName());
            modelMap.addAttribute("product", product);
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        }
        return new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath(), modelMap);
    }
}
