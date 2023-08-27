package by.teachmeskills.eshop.controllers;
import by.teachmeskills.eshop.services.ProductService;
import by.teachmeskills.eshop.services.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final ProductService productService = new ProductServiceImpl();

    @GetMapping("/{id}")
    public ModelAndView getCategoryProductsPage(@PathVariable int id) {
       return productService.getProductsByCategory(id);
    }
}
