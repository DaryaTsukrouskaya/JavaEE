package by.teachmeskills.shop;


import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("category.jsp");
        req.setAttribute("categoryName", req.getParameter("name"));
        try {
            req.setAttribute("categoryProducts", CRUDUtils.getCategoryProducts(req.getParameter("name")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        requestDispatcher.forward(req, resp);
    }
}
