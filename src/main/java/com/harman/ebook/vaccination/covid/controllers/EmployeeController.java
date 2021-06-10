package com.harman.ebook.vaccination.covid.controllers;

import com.harman.ebook.vaccination.covid.domain.PersonRegisterDTO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.EmployeeDashboardVO;
import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;


@RestController

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "${spring.data.rest.basePath}", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;

		/**
	 *
	 * @param empId
	 * @return list of person objects associated with empId
	 */
	@GetMapping(value = "/api/employee/{empId}/dashboard")
	public GenericResponseEntity getEmployeeDashboard(@PathVariable(name = "empId",required = true)Integer empId) {
		return empService.getEmployeeDashboardResponse(empId);
	}


	/**
	 *
	 * @param personRegisterDTO
	 * @return
	 */
	@PostMapping(value = "/api/employee/{empId}/register")
	public GenericResponseEntity registerEmployee(
			@PathVariable(name = "empId",required = true)Integer empId,
			@RequestBody PersonRegisterDTO personRegisterDTO) {
		return empService.registerEmployee(personRegisterDTO,empId);
	}

}
