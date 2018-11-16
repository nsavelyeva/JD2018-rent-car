package com.savelyeva.servlet;

import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.VehicleDto;
import com.savelyeva.model.Vehicle;
import com.savelyeva.service.CarService;
import com.savelyeva.service.LorryService;
import com.savelyeva.service.VehicleService;
import com.savelyeva.validator.PaginationValidator;
import com.savelyeva.validator.VehicleValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/search/vehicle")
public class SearchVehiclesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VehicleValidator vehicleValidator = VehicleValidator.builder().request(request).build();
        VehicleDto vehicleDto = vehicleValidator.validateRequestParameters().getVehicleDto();
        if (vehicleDto != null) {
            if (vehicleDto.getId() != null) {
                Vehicle vehicle = VehicleService.getInstance()
                        .find(vehicleDto.getId());
                request.setAttribute("vehicle", vehicle);
                request.setAttribute("car", CarService.getInstance().find(vehicle.getId()));
                request.setAttribute("lorry", LorryService.getInstance().find(vehicle.getId()));
            } else {
                PaginationValidator paginationValidator = PaginationValidator.builder().request(request).build();
                PaginationDto paginationDto = paginationValidator.validateRequestParameters().getPaginationDto();

                List<Vehicle> vehicles = VehicleService.getInstance()
                        .findByAttributes(vehicleDto, paginationDto);
                request.setAttribute("elements", vehicles);
                request.setAttribute("start", paginationDto.getLimit() * (paginationDto.getPage() - 1) + 1);
            }
        }
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/" + vehicleValidator.getJspPageName())
                .forward(request, response);
    }
}
