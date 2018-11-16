package com.savelyeva.servlet;

import com.savelyeva.model.Vehicle;
import com.savelyeva.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/vehicles")
public class VehicleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Vehicle> vehicles = VehicleService.getInstance().findAll();
        request.setAttribute("elements", vehicles);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/elements.jsp")
                .forward(request, response);
    }
}
