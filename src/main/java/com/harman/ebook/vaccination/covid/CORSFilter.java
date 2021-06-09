package com.harman.ebook.vaccination.covid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.SESSION_EXPIRED;
import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.USER_NOT_LOGGED_IN;

@Component
@RequiredArgsConstructor
public class CORSFilter implements Filter {

    @Autowired
    ApplicationResponseService responseService;

    private final ObjectMapper mapper;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, PATCH, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "*");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        if (request.getServletPath().contains("/api/auth/employee/")) {
            chain.doFilter(request, res);
            return;
        }
        if ( ObjectUtils.isEmpty(session.getAttribute("EMPLOYEE_ID"))) {
            GenericResponseEntity genericResponseEntity = responseService.genFailureResponse(USER_NOT_LOGGED_IN, null);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), genericResponseEntity);
        } else {
            long sessionCreationTime = session.getCreationTime();
            LocalDateTime sessionCreateDtTime =
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(sessionCreationTime), ZoneId.systemDefault());
            LocalDateTime currDtTime = LocalDateTime.now();
            long until = sessionCreateDtTime.until(currDtTime, ChronoUnit.SECONDS);
            if (until > 180) {
                session.invalidate();
                GenericResponseEntity genericResponseEntity = responseService.genFailureResponse(SESSION_EXPIRED, null);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                mapper.writeValue(response.getWriter(), genericResponseEntity);
            } else {
                chain.doFilter(request, res);
            }
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}