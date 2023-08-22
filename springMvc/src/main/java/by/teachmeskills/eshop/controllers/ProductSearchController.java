package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.enums.PagesPathEnum;
import by.teachmeskills.eshop.services.ProductService;
import by.teachmeskills.eshop.services.impl.ProductServiceImpl;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/search")
public class ProductSearchController {
    private final Logger log = LogManager.getLogger(ProductSearchController.class);
    private static final ProductService productService = new ProductServiceImpl();

    @GetMapping
    public ModelAndView getSearchPage() {
        return new ModelAndView(PagesPathEnum.SEARCH_PAGE.getPath());
    }

    @PostMapping
    public ModelAndView searchResult(@RequestParam("keyWords") String keyWords) {
        ModelMap modelMap = new ModelMap();
        if (keyWords != null) {
            try {
                List<Product> products = productService.findProductsByKeywords(keyWords);
                modelMap.addAttribute("products", products);
            } catch (DBConnectionException e) {
                log.error(e.getMessage());
            }
        }
        return new ModelAndView(PagesPathEnum.SEARCH_PAGE.getPath(), modelMap);
    }
}
