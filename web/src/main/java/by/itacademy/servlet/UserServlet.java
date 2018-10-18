package by.itacademy.servlet;

import by.itacademy.model.User;
import by.itacademy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User defaultUser = UserService.getInstance().getDefaultUser();
        request.setAttribute("user", defaultUser);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/user.jsp")
                .forward(request, response);
    }
}
