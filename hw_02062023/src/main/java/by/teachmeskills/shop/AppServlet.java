package by.teachmeskills.shop;

import by.teachmeskills.shop.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;


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
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        if (name != null && password != null) {
            try {
                User user = null;
                req.setAttribute("categories", CRUDUtils.getCategories());
                user = CRUDUtils.getUser(name);
                if (user != null && user.getPassword().equals(password)) {
                    req.getSession().setAttribute("user", user);
                } else if (user == null) {
                    user = new User(name, password);
                    CRUDUtils.createUser(user);
                    req.getSession().setAttribute("user", user);
                } else {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
                    requestDispatcher.forward(req, resp);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
