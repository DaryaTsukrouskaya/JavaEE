package by.teachmeskills.shop;

import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.model.User;
import by.teachmeskills.shop.utils.CRUDUtils;
import by.teachmeskills.shop.utils.State;
import by.teachmeskills.shop.utils.validatorUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("newUsername");
        String surname = req.getParameter("newUserSurname");
        String birthDay = req.getParameter("birthDay");
        String birthMonth = req.getParameter("birthMonth");
        String birthYear = req.getParameter("birthYear");
        LocalDate birthDate = LocalDate.of(Integer.parseInt(birthYear), Integer.parseInt(birthMonth), Integer.parseInt(birthDay));
        String email = req.getParameter("newUserEmail");
        String password = req.getParameter("newUserPassword");
        String repPassword = req.getParameter("repeatPassword");
        String state = validatorUtils.credentialValidation(name, surname, birthDate, email, password, repPassword);
        req.setAttribute("state", state);
        if (state.equals(State.VALID.getState())) {
            User user = new User(name, surname, birthDate, email, password);
            CRUDUtils.createUser(user);
            try {
                req.setAttribute("categories", CRUDUtils.getCategories());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
                requestDispatcher.forward(req, resp);
            } catch (ExecuteQueryException e) {
                System.out.println(e.getMessage());
            }
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
