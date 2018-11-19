package com.savelyeva.web.validator;

import com.savelyeva.database.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Data
@AllArgsConstructor
@Builder
public class PersonValidator {

    private HttpServletRequest request;

    private String jspPageName;

    private PersonDto personDto;

    public PersonValidator(HttpServletRequest request) {
        this.request = request;
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    public PersonValidator validateRequestParameters() {
        String paramId = this.request.getParameter("id");
        String paramEmail = this.request.getParameter("email");
        String paramGender = this.request.getParameter("gender");
        String paramForeigners = this.request.getParameter("foreigners");

        if (paramId != null) {
            this.jspPageName = "person.jsp";
            this.personDto = PersonDto.builder()
                    .id(Long.parseLong(paramId))
                    .build();
        } else if (paramEmail != null || paramGender != null || paramForeigners != null) {
            this.jspPageName = "elements.jsp";
            this.personDto = PersonDto.builder()
                    .email(paramEmail == null ? StringUtils.EMPTY : URLDecoder.decode(paramEmail, "UTF-8"))
                    .gender(paramGender == null ? StringUtils.EMPTY : paramGender)
                    .foreigners(paramForeigners == null ? StringUtils.EMPTY : paramForeigners)
                    .build();
        } else {
            this.jspPageName = "searchpersons.jsp";
            this.personDto = null;
        }
        return this;
    }

}
