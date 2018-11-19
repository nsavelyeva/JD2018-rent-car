package com.savelyeva.web.servlet;

import com.savelyeva.database.model.DrivingLicense;
import com.savelyeva.service.services.DrivingLicenseService;
import com.savelyeva.web.util.ApplicationContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/drivinglicenses")
public class DrivingLicenseServlet extends HttpServlet {

    private DrivingLicenseService drivingLicenseService = ApplicationContextHolder.getBean("drivingLicenseService", DrivingLicenseService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DrivingLicense> drivingLicenses = drivingLicenseService.getAllDrivingLicenses();
        request.setAttribute("elements", drivingLicenses);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/elements.jsp")
                .forward(request, response);
    }
}
