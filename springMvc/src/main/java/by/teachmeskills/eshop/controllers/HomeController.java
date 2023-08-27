package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.enums.PagesPathEnum;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.UserService;
import by.teachmeskills.eshop.services.impl.CategoryServiceImpl;
import by.teachmeskills.eshop.services.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final CategoryServiceImpl categoryService = new CategoryServiceImpl();
    private final Logger log = LogManager.getLogger(HomeController.class);

    @GetMapping
    public ModelAndView getHomePage() {
        return categoryService.returnCategoriesForHomePage();
    }
}
