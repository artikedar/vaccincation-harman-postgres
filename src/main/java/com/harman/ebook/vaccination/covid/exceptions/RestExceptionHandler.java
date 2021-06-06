/*
 *
 * #  * Copyright (c) Nielsen
 * #  * @Author Bhagwat robh9001
 *
 */
package com.harman.ebook.vaccination.covid.exceptions;

import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import java.util.Iterator;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    public static final HttpStatus HTTP_ERROR_STATUS = HttpStatus.OK;

    @Autowired
    ApplicationResponseService applicationResponseService;

    @ExceptionHandler(Exception.class)
    public final GenericResponseEntity handleAllExceptions(final Exception ex, final HttpServletRequest request) {
        log.error("Exception : " + stackTraceToString(ex));

        String errorMessage = ex.getMessage();
        String errorCode = "INGESTION_SERVICES_ERROR";
        GenericResponseEntity errorResponse = null;
        if (ex instanceof DataIntegrityViolationException) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException exDetail = (ConstraintViolationException) ex.getCause();
                if (ObjectUtils.isNotEmpty(exDetail) && ObjectUtils.isNotEmpty(exDetail.getConstraintName())) {
                    errorMessage = "getConstraintVoilationMessage(exDetail.getConstraintName())";
                    if (ObjectUtils.isEmpty(errorMessage)) {
                        String jdbcExceptionMsg = exDetail.getSQLException().getMessage();

                    }
                }
            } else if (ex instanceof NonTransientDataAccessException) {
                NonTransientDataAccessException nex = (NonTransientDataAccessException) ex;
                String rangeMismatchMessage = nex.getMostSpecificCause().getMessage();
                if (ObjectUtils.isNotEmpty(nex) && nex.getMostSpecificCause().getMessage().contains("\n")) {
                    errorMessage = nex.getMostSpecificCause().getMessage().substring(0, nex.getMostSpecificCause().getMessage().lastIndexOf('\n'));
                } else if (rangeMismatchMessage.contains("too long") ) {
                    errorMessage = "One of the property length is too long or too short";
                    errorCode = "COLUMN_LEN_VALIDATION_EXCEPTION";
                }
            } else if (ex instanceof DataException) {
                DataException dex = (DataException) ex;
                errorMessage = dex.getCause().getMessage();
            }
        }
        errorResponse = buildGenericExceptionResponse(errorCode, errorMessage, HTTP_ERROR_STATUS);

        //log exception here
        ex.printStackTrace();
        return errorResponse;
    }


    @ExceptionHandler(TransactionSystemException.class)
    protected GenericResponseEntity handleTransactionException(TransactionSystemException ex) throws Throwable {
        Throwable cause = ex.getCause();
        if (!(cause instanceof RollbackException)) {
            throw cause;
        }
        if (!(cause.getCause() instanceof ConstraintViolationException)) {
            throw cause.getCause();
        }
        //log exception
        ex.printStackTrace();
        javax.validation.ConstraintViolationException validationException =
                (javax.validation.ConstraintViolationException) cause.getCause();
        StringBuilder messages = new StringBuilder();
        Iterator<ConstraintViolation<?>> iterator = validationException.getConstraintViolations().iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<?> constraintViolation = iterator.next();
            messages.append(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage() + '\n');
        }
        return buildGenericExceptionResponse("ERROR_TRANSACTION", messages.toString(), HTTP_ERROR_STATUS);
    }

    private GenericResponseEntity buildGenericExceptionResponse(String errorCode, String message,
        HttpStatus status) {
        return applicationResponseService.genFailureResponse(message, "");

    }


    /**
     *
     * @param  e
     * @return stringify exception
     */
    public String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getClass().getName() + ": " + e.getMessage() + "\n");
         StackTraceElement[] element = e.getStackTrace();
        for(int i=0;i<=30;i++){
           if( ObjectUtils.isNotEmpty(element[i])) {
                sb.append(element[i].toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
