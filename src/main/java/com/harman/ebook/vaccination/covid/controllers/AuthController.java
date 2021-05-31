package com.harman.ebook.vaccination.covid.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "${spring.data.rest.basePath}", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AuthController {
	@Autowired
	EmployeeService empService;
	
	@Autowired
	ApplicationResponseService appResponseService;
	
	@GetMapping("/api/auth/{empId}/signin/{doj}")
	public GenericResponseEntity validateSignIn (
			@PathVariable (name="empId",required = true)Integer empId,
			@PathVariable (name="doj",required = true) String doj)throws Exception {
		System.out.println("inside signIn");
		boolean isValidUser=false;
		GenericResponseEntity response=null;
		EmployeeMaster empInfo=null;
		Date date1=new SimpleDateFormat("yyyy-dd-mm").parse(doj);
		
		empInfo=empService.findByEmployeeId(empId);
		if(null != empInfo && empInfo.getIsactive()) {
			String strDate = empInfo.getDateOfJoining().toString().substring(0, 10);
			Date date2=new SimpleDateFormat("yyyy-dd-mm").parse(strDate);
			System.out.println("request : doj - "+date1+" db : "+date2+" xyz-"+strDate);
			if(date1.equals(date2)) {
				isValidUser=true;
			}
		}
		if(isValidUser) {
			response = appResponseService.genSuccessResponse(VaccinationConstants.VALID_USER, null);
		}else {
			response = appResponseService.genFailureResponse(VaccinationConstants.INVALID_USER, null);
		}
		
		return response;
	}
	

}
