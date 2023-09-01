package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.enums.PagesPathEnum;
import by.teachmeskills.eshop.services.UserService;
import by.teachmeskills.eshop.services.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SessionAttributes({"user"})
@RequestMapping("/login")
public class LoginController {
    private final UserService userService = new UserServiceImpl();

    @GetMapping
    public ModelAndView openLoginPage() {
        return new ModelAndView(PagesPathEnum.SIGN_IN_PAGE.getPath());
    }

    @PostMapping
    public ModelAndView login(@ModelAttribute("user") User user) {
        return userService.authenticate(user.getEmail(), user.getPassword());
    }

    @ModelAttribute("user")
    public User setUpUser() {
        return new User();
    }
}
