package com.savelyeva.web.validator;

import com.savelyeva.database.dto.PaginationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
@AllArgsConstructor
@Builder
public class PaginationValidator {

    public static final int DEFAULT_LIMIT = 5;

    private HttpServletRequest request;

    private PaginationDto paginationDto;

    public PaginationValidator(HttpServletRequest request) {
        this.request = request;
    }

    public PaginationValidator validateRequestParameters() {
        String paramLimit = this.request.getParameter("limit");
        String paramPage = this.request.getParameter("page");

        Integer limit = paramLimit == null ? DEFAULT_LIMIT : Integer.parseInt(paramLimit);
        Integer page = paramPage == null ? 0 : Integer.parseInt(paramPage);

        this.paginationDto = PaginationDto.builder().page(page).limit(limit).build();
        return this;
    }

}


