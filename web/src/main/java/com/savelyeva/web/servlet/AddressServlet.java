package com.savelyeva.web.servlet;

import com.savelyeva.database.model.Address;
import com.savelyeva.service.services.AddressService;
import com.savelyeva.web.util.ApplicationContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addresses")
public class AddressServlet extends HttpServlet {

    private AddressService addressService = ApplicationContextHolder.getBean("addressService", AddressService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Address> addresses = addressService.getAllAddresses();
        request.setAttribute("elements", addresses);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/elements.jsp")
                .forward(request, response);
    }
}
