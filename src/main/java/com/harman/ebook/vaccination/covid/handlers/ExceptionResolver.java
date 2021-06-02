package com.harman.ebook.vaccination.covid.handlers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.exceptions.AppointmentExistException;
import com.harman.ebook.vaccination.covid.exceptions.BasicAppointmentInputException;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;

import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class ExceptionResolver extends ResponseEntityExceptionHandler {
	@Autowired
	ApplicationResponseService responseService;

	@ExceptionHandler(value = { AppointmentExistException.class })
	public GenericResponseEntity handleAppointmentExistException(Exception ex, WebRequest req) {
		//ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));
		return responseService.genFailureResponse(VaccinationConstants.APPOINTMENT_EXIST_ALREADY, null);
	}
	
	@ExceptionHandler(value = { BasicAppointmentInputException.class })
	public GenericResponseEntity handleBasicAppointmentInputException(Exception ex, WebRequest req) {
		//ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));
		return responseService.genFailureResponse(VaccinationConstants.INVALID_INPUT_ARGS_ALL_MANDATORY, null);
	}
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
