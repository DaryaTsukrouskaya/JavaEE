package by.teachmeskills.shop;

import by.teachmeskills.shop.model.User;
import by.teachmeskills.shop.utils.CRUDUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class AppServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (((User) req.getSession().getAttribute("user")).getName().equals("isEmpty")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        if (email != null && password != null) {
            try {
                req.setAttribute("categories", CRUDUtils.getCategories());
                User user = CRUDUtils.getUser(email);
                if (user != null && user.getPassword().equals(password)) {
                    req.getSession().setAttribute("user", user);
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
                    req.setAttribute("state", "Неверный логин или пароль");
                    requestDispatcher.forward(req, resp);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            req.setAttribute("state", "Неверный логин или пароль");
            requestDispatcher.forward(req, resp);
        }
    }
}
