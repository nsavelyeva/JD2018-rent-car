package com.savelyeva.web.servlet;

import com.savelyeva.database.dto.PaginationDto;
import com.savelyeva.database.dto.VehicleDto;
import com.savelyeva.database.model.Car;
import com.savelyeva.database.model.Lorry;
import com.savelyeva.database.model.Vehicle;
import com.savelyeva.service.services.CarService;
import com.savelyeva.service.services.LorryService;
import com.savelyeva.service.services.VehicleService;
import com.savelyeva.web.util.ApplicationContextHolder;
import com.savelyeva.web.validator.PaginationValidator;
import com.savelyeva.web.validator.VehicleValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet("/search/vehicle")
public class SearchVehiclesServlet extends HttpServlet {

    private VehicleService vehicleService = ApplicationContextHolder.getBean("vehicleService", VehicleService.class);

    private CarService carService = ApplicationContextHolder.getBean("carService", CarService.class);

    private LorryService lorryService = ApplicationContextHolder.getBean("lorryService", LorryService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VehicleValidator vehicleValidator = VehicleValidator.builder().request(request).build();
        VehicleDto vehicleDto = vehicleValidator.validateRequestParameters().getVehicleDto();
        if (vehicleDto != null) {
            if (vehicleDto.getId() != null) {
                Vehicle vehicle = vehicleService.find(vehicleDto.getId()).get();
                Optional<Car> car = carService.find(vehicleDto.getId());
                Optional<Lorry> lorry = lorryService.find(vehicleDto.getId());
                request.setAttribute("vehicle", vehicle);
                request.setAttribute("car", car.isPresent() ? car.get() : Car.builder().passengersCapacity((short) 0).trunkCapacity((short) 0).build());
                request.setAttribute("lorry", lorry.isPresent() ? lorry.get() : Lorry.builder().carryingCapacity(0).build());
            } else {
                PaginationValidator paginationValidator = PaginationValidator.builder().request(request).build();
                PaginationDto paginationDto = paginationValidator.validateRequestParameters().getPaginationDto();

                List<Vehicle> vehicles = vehicleService.findByAttributes(vehicleDto, paginationDto);
                request.setAttribute("elements", vehicles);
                request.setAttribute("start", paginationDto.getLimit() * (paginationDto.getPage() - 1) + 1);
            }
        }
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/" + vehicleValidator.getJspPageName())
                .forward(request, response);
    }
}
