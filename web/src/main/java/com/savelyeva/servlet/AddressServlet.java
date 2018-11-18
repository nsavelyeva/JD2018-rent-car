package com.savelyeva.servlet;

import com.savelyeva.model.Address;
import com.savelyeva.service.AddressService;
import com.savelyeva.util.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addresses")
public class AddressServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddressService addressService = (AddressService) ApplicationContext.getInstance().getBean("AddressService");
        List<Address> addresses = addressService.getAllAddresses();
        request.setAttribute("elements", addresses);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/elements.jsp")
                .forward(request, response);
    }

    public static void main(String[] args) {
        AddressService addressService = (AddressService) ApplicationContext.getInstance().getBean("AddressService");
        List<Address> addresses = addressService.getAllAddresses();
    }
}
