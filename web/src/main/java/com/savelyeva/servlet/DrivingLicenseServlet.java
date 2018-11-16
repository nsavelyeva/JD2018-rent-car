package com.savelyeva.servlet;

import com.savelyeva.model.DrivingLicense;
import com.savelyeva.service.DrivingLicenseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/drivinglicenses")
public class DrivingLicenseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DrivingLicense> drivingLicenses = DrivingLicenseService.getInstance().getAllDrivingLicenses();
        request.setAttribute("elements", drivingLicenses);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/elements.jsp")
                .forward(request, response);
    }
}
