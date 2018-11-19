package com.savelyeva.web.servlet;

import com.savelyeva.database.dto.PaginationDto;
import com.savelyeva.database.dto.PersonDto;
import com.savelyeva.database.model.Person;
import com.savelyeva.service.services.PersonService;
import com.savelyeva.web.util.ApplicationContextHolder;
import com.savelyeva.web.validator.PaginationValidator;
import com.savelyeva.web.validator.PersonValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet("/search/person")
public class SearchPersonsServlet extends HttpServlet {

    private PersonService personService = ApplicationContextHolder.getBean("personService", PersonService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonValidator personValidator = PersonValidator.builder().request(request).build();
        PersonDto personDto = personValidator.validateRequestParameters().getPersonDto();
        if (personDto != null) {
            if (personDto.getId() != null) {
                Optional<Person> person = personService.find(personDto.getId());
                request.setAttribute("person", person.get());
            } else {
                PaginationValidator paginationValidator = PaginationValidator.builder().request(request).build();
                PaginationDto paginationDto = paginationValidator.validateRequestParameters().getPaginationDto();

                List<Person> persons = personService.findByAttributes(personDto, paginationDto);
                request.setAttribute("elements", persons);
                request.setAttribute("start", paginationDto.getLimit() * (paginationDto.getPage() - 1) + 1);
            }
        }
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/" + personValidator.getJspPageName())
                .forward(request, response);
    }
}
