package com.harman.ebook.vaccination.covid.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.service.AuthService;
import com.harman.ebook.vaccination.covid.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "${spring.data.rest.basePath}", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AuthController {
	
	@Autowired
	ApplicationResponseService appResponseService;
	
	@Autowired
	AuthService authService;
	
	@GetMapping("/api/auth/employee/{empId}")
	public GenericResponseEntity validateSignIn (
			@PathVariable (name="empId",required = true)Integer empId,
			@RequestParam(name="doj",required = true) String doj)throws Exception {
		return  authService.validateUser(empId,doj);
	}
}
