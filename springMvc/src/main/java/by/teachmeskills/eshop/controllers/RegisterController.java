package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.enums.PagesPathEnum;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.eshop.services.UserService;
import by.teachmeskills.eshop.services.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SessionAttributes({"user"})
@RequestMapping("/register")
public class RegisterController {
    private final Logger log = LogManager.getLogger(RegisterController.class);
    private static final UserService userService = new UserServiceImpl();

    @GetMapping
    public ModelAndView registerPage() {
        return new ModelAndView(PagesPathEnum.REGISTER_PAGE.getPath());
    }

    @PostMapping
    public ModelAndView registerUser(@ModelAttribute("user") User user, @RequestParam("repeatPassword") String repPass) {
        try {
            return userService.registerUser(user, repPass);
        } catch (DBConnectionException | UserAlreadyExistsException e) {
            log.error(e.getMessage());
            return new ModelAndView(PagesPathEnum.REGISTER_PAGE.getPath(), new ModelMap().addAttribute("state", e.getMessage()));
        }
    }

    @ModelAttribute("user")
    public User setUpUser() {
        return new User();
    }
}
