package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.services.UserService;
import by.teachmeskills.eshop.services.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/profile")
public class UserProfileController {
    private final Logger log = LogManager.getLogger(UserProfileController.class);
    private final UserService userService = new UserServiceImpl();

    @GetMapping
    public ModelAndView getUserPage(@SessionAttribute("user") User user) {
        return userService.userServicePage(user);
    }
}
