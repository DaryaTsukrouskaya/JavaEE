package by.teachmeskills.eshop.services.impl;

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
import by.teachmeskills.eshop.services.UserService;
import by.teachmeskills.eshop.utils.ValidatorUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository = new UserRepositoryImpl();
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
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
    public ModelAndView registerUser(User user, String repPassword) throws DBConnectionException, UserAlreadyExistsException {
        ModelMap modelMap = new ModelMap();
        try {
            ValidatorUtils.validateParamNotNull(user.getName(), user.getSurname(), String.valueOf(user.getBirthDate()), user.getEmail(), user.getPassword(), repPassword);
        } catch (RequestParamNullException e) {
            log.error(e);
            modelMap.addAttribute("state", "Заполните все поля формы");
            return new ModelAndView(PagesPathEnum.REGISTER_PAGE.getPath(), modelMap);
        }
        if (State.VALID.getState().equals(ValidatorUtils.userDataValidation(user.getName(), user.getSurname(), user.getBirthDate(), user.getEmail(), user.getPassword(), repPassword))) {
            userRepository.create(user);
            modelMap.addAttribute("categories", categoryRepository.read());
            return new ModelAndView(PagesPathEnum.HOME_PAGE.getPath(), modelMap);
        } else {
            modelMap.addAttribute("state", "Заполните все поля формы");
            return new ModelAndView(PagesPathEnum.REGISTER_PAGE.getPath(), modelMap);
        }

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
    public ModelAndView authenticate(String email) {
        ModelMap modelMap = new ModelMap();
        User user = null;
        if (email != null) {
            try {
                user = userRepository.findByEmail(email);
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
}
