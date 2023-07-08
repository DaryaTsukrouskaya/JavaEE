package by.teachmeskills.shop;

import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product.jsp");
        try {
            req.setAttribute("product", CRUDUtils.getProductById(Integer.parseInt(req.getParameter("id"))));
            req.setAttribute("productName", CRUDUtils.getProductById(Integer.parseInt(req.getParameter("id"))).getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        requestDispatcher.forward(req, resp);
    }
}
