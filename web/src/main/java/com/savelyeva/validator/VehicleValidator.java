package com.savelyeva.validator;

import com.savelyeva.dto.VehicleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Data
@AllArgsConstructor
@Builder
public class VehicleValidator {

    private HttpServletRequest request;

    private String jspPageName;

    private VehicleDto vehicleDto;

    public VehicleValidator(HttpServletRequest request) {
        this.request = request;
    }

    public VehicleValidator validateRequestParameters() {
        String paramId = this.request.getParameter("id");
        String paramPrice = this.request.getParameter("price");
        String paramTransmission = this.request.getParameter("transmission");
        String paramAvailable = this.request.getParameter("available");

        if (paramId != null) {
            this.jspPageName = "vehicle.jsp";
            this.vehicleDto = VehicleDto.builder()
                    .id(Long.parseLong(paramId))
                    .build();
        } else if (paramPrice != null || paramTransmission != null || paramAvailable != null) {
            this.jspPageName = "elements.jsp";
            this.vehicleDto = VehicleDto.builder()
                    .dayPrice(paramPrice == null ? StringUtils.EMPTY : paramPrice)
                    .transmission(paramTransmission == null ? StringUtils.EMPTY : paramTransmission)
                    .available(paramAvailable == null ? StringUtils.EMPTY : paramAvailable)
                    .build();
        } else {
            this.jspPageName = "searchvehicles.jsp";
            this.vehicleDto = null;
        }
        return this;
    }

}
