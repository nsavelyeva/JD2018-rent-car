package com.savelyeva.servlet;

import com.savelyeva.model.Rent;
import com.savelyeva.service.DrivingLicenseService;
import com.savelyeva.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rents")
public class RentServlet extends HttpServlet {

    @Autowired
    private RentService rentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rent> rents = rentService.getAllRents();
        request.setAttribute("elements", rents);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/elements.jsp")
                .forward(request, response);
    }
}
