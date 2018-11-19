package com.savelyeva.web.servlet;

import com.savelyeva.database.model.Person;
import com.savelyeva.service.services.PersonService;
import com.savelyeva.web.util.ApplicationContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/persons")
public class PersonServlet extends HttpServlet {

    private PersonService personService = ApplicationContextHolder.getBean("personService", PersonService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Person> persons = personService.findAll();
        request.setAttribute("elements", persons);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/elements.jsp")
                .forward(request, response);
    }
}
