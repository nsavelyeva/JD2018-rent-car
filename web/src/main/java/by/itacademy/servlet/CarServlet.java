package by.itacademy.servlet;

import by.itacademy.model.Car;
import by.itacademy.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/car")
public class CarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Car defaultCar = CarService.getInstance().getDefaultCar();
        request.setAttribute("car", defaultCar);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/car.jsp")
                .forward(request, response);
    }
}
