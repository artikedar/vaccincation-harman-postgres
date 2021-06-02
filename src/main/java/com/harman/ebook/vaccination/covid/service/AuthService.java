package com.harman.ebook.vaccination.covid.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;

@Service
public class AuthService {
	
	@Autowired
	EmployeeService empService;
	
	public EmployeeMaster validateUser(Integer empId, String doj) throws Exception {
		boolean isValidUser=false;
		EmployeeMaster empInfo=null;
		Date reqDoj=new SimpleDateFormat("yyyy-dd-mm").parse(doj);
		
		empInfo=empService.findByEmployeeId(empId);
		if(null != empInfo && empInfo.getIsactive()) {
			String strDate = empInfo.getDateOfJoining().toString().substring(0, 10);
			Date dbDoj=new SimpleDateFormat("yyyy-dd-mm").parse(strDate);
			return (reqDoj.equals(dbDoj))?empInfo:null;
		}
		return null;
	}
}
