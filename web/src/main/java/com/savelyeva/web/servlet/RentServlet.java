package com.savelyeva.web.servlet;

import com.savelyeva.database.model.Rent;
import com.savelyeva.service.services.RentService;
import com.savelyeva.web.util.ApplicationContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rents")
public class RentServlet extends HttpServlet {

    private RentService rentService = ApplicationContextHolder.getBean("rentService", RentService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rent> rents = rentService.getAllRents();
        request.setAttribute("elements", rents);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/elements.jsp")
                .forward(request, response);
    }
}
