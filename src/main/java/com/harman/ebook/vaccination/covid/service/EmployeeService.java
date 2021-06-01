package com.harman.ebook.vaccination.covid.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harman.ebook.vaccination.covid.domain.EmployeeDashboardVO;
import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
import com.harman.ebook.vaccination.covid.entity.Person;
import com.harman.ebook.vaccination.covid.repository.MasterEmpRespository;
import com.harman.ebook.vaccination.covid.repository.PersonRespository;

@Service
public class EmployeeService {
	@Autowired
	 MasterEmpRespository emprepos;
	
	@Autowired
	PersonRespository personRepository;
	
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
	
	public List<EmployeeDashboardVO> getEmployeeDashboard(Integer empId) {
        List<Person> person = personRepository.findPersonByEmpMasterId(empId);
        List<EmployeeDashboardVO> vo = getEmployeeDashboardVO(person);
        return vo;
    }
	
	private List<EmployeeDashboardVO> getEmployeeDashboardVO(List<Person> personList) {
        List<EmployeeDashboardVO> voList = new ArrayList<>();
        for (Person person: personList)
        {
            EmployeeDashboardVO employeeDashboardVO = new EmployeeDashboardVO();
            employeeDashboardVO.setPersonId(person.getPersonId());
            employeeDashboardVO.setEmpMasterId(person.getEmpMasterId());
            employeeDashboardVO.setFullName(person.getFullName());
            employeeDashboardVO.setLocation(person.getLocation());
            employeeDashboardVO.setDateOfDoseI(person.getDateOfDoseI());
            employeeDashboardVO.setDateOfDoseII(person.getDateOfDoseII());
            employeeDashboardVO.setIsDoseI(person.getIsDoseI());
            employeeDashboardVO.setIsDoseII(person.getIsDoseII());
            employeeDashboardVO.setVacType(person.getVacType());
            employeeDashboardVO.setPersonAge(person.getPersonAge());
            voList.add(employeeDashboardVO);
        };
        return voList;
    }

}
