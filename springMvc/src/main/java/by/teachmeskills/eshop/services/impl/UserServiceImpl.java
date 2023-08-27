package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.enums.PagesPathEnum;
import by.teachmeskills.eshop.enums.State;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.ExecuteQueryException;
import by.teachmeskills.eshop.exceptions.RequestParamNullException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import by.teachmeskills.eshop.repositories.UserRepository;
import by.teachmeskills.eshop.repositories.impl.CategoryRepositoryImpl;
import by.teachmeskills.eshop.repositories.impl.UserRepositoryImpl;
import by.teachmeskills.eshop.services.ProductService;
import by.teachmeskills.eshop.services.UserService;
import by.teachmeskills.eshop.utils.ValidatorUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository = new UserRepositoryImpl();
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final static Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public List<User> read() throws DBConnectionException {
        return userRepository.read();
    }

    @Override
    public void create(User user) throws DBConnectionException, UserAlreadyExistsException {
        userRepository.create(user);
    }

    @Override
    public ModelAndView registerUser(User user, String repPassword) {
        ModelMap modelMap = new ModelMap();
        HashMap<String, String> userData = new HashMap<>();
        userData.put("name", user.getName());
        userData.put("surname", user.getSurname());
        userData.put("email", user.getEmail());
        userData.put("surname", user.getSurname());
        userData.put("password", user.getPassword());
        userData.put("repPassword", repPassword);
        try {
            if (State.VALID.getState().equals(ValidatorUtils.userDataValidation(userData, user.getBirthDate()))) {
                userRepository.create(user);
                modelMap.addAttribute("categories", categoryRepository.read());
            } else {
                throw new RequestParamNullException("Заполните поля формы");
            }
        } catch (RequestParamNullException e) {
            modelMap.addAttribute("state", "Заполните все поля формы");
            return new ModelAndView(PagesPathEnum.REGISTER_PAGE.getPath(), modelMap);
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        } catch (UserAlreadyExistsException e) {
            modelMap.addAttribute("state", "Пользователь с такой почтой уже существует");
            return new ModelAndView(PagesPathEnum.REGISTER_PAGE.getPath(), modelMap);
        }
        return new ModelAndView(PagesPathEnum.HOME_PAGE.getPath(), modelMap);
    }

    @Override
    public void delete(int id) throws DBConnectionException {
        userRepository.delete(id);

    }

    @Override
    public User findById(int id) throws ExecuteQueryException, DBConnectionException {
        return userRepository.findById(id);
    }

    @Override
    public ModelAndView authenticate(String email, String password) {
        ModelMap modelMap = new ModelMap();
        User user = null;
        if (email != null) {
            try {
                user = userRepository.findByEmailAndPassword(email, password);
                if (Optional.ofNullable(user).isPresent()) {
                    modelMap.addAttribute("user", user);
                    modelMap.addAttribute("categories", categoryRepository.read());
                } else {
                    modelMap.addAttribute("state", "Неверный логин или пароль");
                    return new ModelAndView(PagesPathEnum.SIGN_IN_PAGE.getPath(), modelMap);
                }
            } catch (DBConnectionException | ExecuteQueryException e) {
                log.error(e.getMessage());
            }
        } else {
            modelMap.addAttribute("state", "Заполните все поля формы");
            return new ModelAndView(PagesPathEnum.SIGN_IN_PAGE.getPath(), modelMap);
        }
        return new ModelAndView(PagesPathEnum.HOME_PAGE.getPath(), modelMap);
    }

    @Override
    public void updatePassword(String password, String email) throws DBConnectionException {
        userRepository.updatePassword(password, email);

    }

    @Override
    public void updateEmail(String previousEmail, String newEmail) throws DBConnectionException {
        userRepository.updateEmail(previousEmail, newEmail);

    }

    @Override
    public ModelAndView userServicePage(User user) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("user", user);
        List<Product> productList1 = new ArrayList<>();
        List<Product> productList2 = new ArrayList<>();
        try {
            productList1.add(productService.findById(1));
            productList1.add(productService.findById(2));
            productList1.add(productService.findById(3));
            productList1.add(productService.findById(4));
            productList2.add(productService.findById(3));
            productList2.add(productService.findById(1));
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        }

        Order order1 = new Order(1, LocalDateTime.now(), productList1);
        Order order2 = new Order(2, LocalDateTime.now(), productList2);
        List<Order> userOrders = new ArrayList<>();
        userOrders.add(order1);
        userOrders.add(order2);
        modelMap.addAttribute("userOrders", userOrders);
        return new ModelAndView(PagesPathEnum.USER_PROFILE_PAGE.getPath(), modelMap);
    }
}
