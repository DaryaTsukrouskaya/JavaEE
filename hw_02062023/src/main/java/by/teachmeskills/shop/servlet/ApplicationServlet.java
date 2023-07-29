package by.teachmeskills.shop.servlet;

import by.teachmeskills.shop.commands.BaseCommand;
import by.teachmeskills.shop.commands.CategoryProductPageCommand;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.utils.CommandFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebServlet("/eshop")
public class ApplicationServlet extends HttpServlet {
    private final static Logger log = LogManager.getLogger(CategoryProductPageCommand.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseCommand command = CommandFactory.defineCommand(request);
        String path;
        try {
            path = command.execute(request);
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (CommandException e) {
            log.error(e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.SIGN_IN_PAGE.getPath()).forward(request, response);
        }

    }
}
