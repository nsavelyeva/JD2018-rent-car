package com.savelyeva.servlet;

import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.PersonDto;
import com.savelyeva.model.Person;
import com.savelyeva.service.LorryService;
import com.savelyeva.service.PersonService;
import com.savelyeva.validator.PaginationValidator;
import com.savelyeva.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private PersonService personService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonValidator personValidator = PersonValidator.builder().request(request).build();
        PersonDto personDto = personValidator.validateRequestParameters().getPersonDto();
        if (personDto != null) {
            if (personDto.getId() != null) {
                Optional<Person> person = personService.find(personDto.getId());
                request.setAttribute("person", person);
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
