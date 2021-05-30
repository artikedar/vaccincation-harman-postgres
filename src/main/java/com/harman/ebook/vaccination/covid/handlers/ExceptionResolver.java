package com.harman.ebook.vaccination.covid.handlers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class ExceptionResolver extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest req) {
		//log.error("Exception: ", ex.getMessage());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
