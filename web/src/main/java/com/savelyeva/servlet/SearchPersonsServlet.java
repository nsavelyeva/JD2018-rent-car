package com.savelyeva.servlet;

import com.savelyeva.model.Person;
import com.savelyeva.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@WebServlet("/search/person")
public class SearchPersonsServlet extends HttpServlet {

    public static final int DEFAULT_LIMIT = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramId = request.getParameter("id");
        String paramEmail = request.getParameter("email");
        String paramGender = request.getParameter("gender");
        String paramForeigners = request.getParameter("foreigners");
        String paramLimit = request.getParameter("limit");
        String paramPage = request.getParameter("page");

        String jspPageName;
        if (paramId != null) {
            jspPageName = "person.jsp";
            Long id = Long.parseLong(paramId);
            Person person = PersonService.getInstance().find(id);
            request.setAttribute("person", person);
        } else if (paramEmail != null || paramGender != null || paramForeigners != null) {
            jspPageName = "elements.jsp";
            Integer limit = paramLimit == null ? DEFAULT_LIMIT : Integer.parseInt(paramLimit);
            Integer page = paramPage == null ? 0 : Integer.parseInt(paramPage);
            List<Person> persons = PersonService.getInstance()
                    .findByAttributes(paramEmail == null ? "" : URLDecoder.decode(paramEmail, "UTF-8"),
                            paramGender == null ? "" : paramGender,
                            paramForeigners == null ? "" : paramForeigners,
                            limit,
                            page);
            request.setAttribute("elements", persons);
            request.setAttribute("start", limit * (page - 1) + 1);
        } else {
            jspPageName = "searchpersons.jsp";
        }

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/" + jspPageName)
                .forward(request, response);
    }
}
