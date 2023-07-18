package by.teachmeskills.shop;

import by.teachmeskills.shop.exceptions.ExecuteQueryException;
import by.teachmeskills.shop.listener.DBConnectionManager;
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
        ServletContext servletContext = getServletContext();
        DBConnectionManager dbConnectionManager = (DBConnectionManager) servletContext.getAttribute("DBManager");
        Connection connection = dbConnectionManager.getConnection();
        try {
            req.setAttribute("product", CRUDUtils.getProductById(Integer.parseInt(req.getParameter("id")), connection));
            req.setAttribute("productName", CRUDUtils.getProductById(Integer.parseInt(req.getParameter("id")), connection).getName());
        } catch (ExecuteQueryException e) {
            System.out.println(e.getMessage());
        }
        requestDispatcher.forward(req, resp);
    }
}
