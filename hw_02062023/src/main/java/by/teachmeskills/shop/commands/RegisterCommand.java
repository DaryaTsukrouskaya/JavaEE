package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.enums.State;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.exceptions.RequestParamNullException;
import by.teachmeskills.shop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.UserService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import by.teachmeskills.shop.services.impl.UserServiceImpl;
import by.teachmeskills.shop.utils.ValidatorUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;

public class RegisterCommand implements BaseCommand {
    private final static Logger log = LogManager.getLogger(CategoryProductPageCommand.class);
    private static final UserService userService = new UserServiceImpl();
    private static final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        String name = req.getParameter("newUsername");
        String surname = req.getParameter("newUserSurname");
        String birthDay = req.getParameter("birthDay");
        String birthMonth = req.getParameter("birthMonth");
        String birthYear = req.getParameter("birthYear");
        String email = req.getParameter("newUserEmail");
        String password = req.getParameter("newUserPassword");
        String repPassword = req.getParameter("repeatPassword");
        try {
            ValidatorUtils.validateParamNotNull(name, surname, birthDay, birthMonth, birthYear, email, password, repPassword);
        } catch (RequestParamNullException e) {
            return PagesPathEnum.REGISTER_PAGE.getPath();
        }
        LocalDate birthDate = LocalDate.of(Integer.parseInt(birthYear), Integer.parseInt(birthMonth), Integer.parseInt(birthDay));
        String state = ValidatorUtils.userDataValidation(name, surname, birthDate, email, password, repPassword);
        req.setAttribute("state", state);
        if (state.equals(State.VALID.getState())) {
            User user = new User(name, surname, birthDate, email, password);
            try {
                userService.create(user);
                req.setAttribute("categories", categoryService.read());
                req.getSession().setAttribute("user", user);
            } catch (DBConnectionException e) {
                log.error(e.getMessage());
            } catch (UserAlreadyExistsException e) {
                req.setAttribute("state", e.getMessage());
                log.error(e.getMessage());
                return PagesPathEnum.REGISTER_PAGE.getPath();
            }
            return PagesPathEnum.HOME_PAGE.getPath();
        } else {
            return PagesPathEnum.REGISTER_PAGE.getPath();
        }
    }
}
