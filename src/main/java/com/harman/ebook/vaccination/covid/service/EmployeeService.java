package com.harman.ebook.vaccination.covid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
import com.harman.ebook.vaccination.covid.repository.MasterEmpRespository;

@Service
public class EmployeeService {
	@Autowired
	 MasterEmpRespository emprepos;
	
	public List<EmployeeMaster> getEmployee() {
		return emprepos.findAll();
	}
	
	public EmployeeMaster findByEmployeeId(Integer id) {
		
		EmployeeMaster employeeRec = emprepos.findByEmployeeId(id);
		if(null == employeeRec)
			System.out.println("null returned from db");
		else
			System.out.println("found entity: "+employeeRec);
		return employeeRec;
	}

}
