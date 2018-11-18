package com.savelyeva.servlet;

import com.savelyeva.model.Person;
import com.savelyeva.service.DrivingLicenseService;
import com.savelyeva.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/persons")
public class PersonServlet extends HttpServlet {

    @Autowired
    private PersonService personService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Person> persons = personService.findAll();
        request.setAttribute("elements", persons);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/elements.jsp")
                .forward(request, response);
    }
}
