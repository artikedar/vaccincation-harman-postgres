package com.harman.ebook.vaccination.covid.service;

import java.util.ArrayList;
import java.util.List;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.EmpVaccAppointmentVO;
import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
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

    @Autowired
    private ApplicationResponseService appResponseService;

    public List<EmployeeMaster> getEmployee() {
        return emprepos.findAll();
    }

    public EmployeeMaster findByEmployeeId(Integer id) {
        EmployeeMaster employeeRec = emprepos.findByEmployeeId(id);
        return employeeRec;
    }

    public GenericResponseEntity getEmployeeDashboardResponse(Integer empId) {
        List<EmployeeDashboardVO> vo = getEmployeeDashboard(empId);
        return appResponseService.genSuccessResponse(VaccinationConstants.RECORD_FOUNDS, vo);
    }

    public List<EmployeeDashboardVO> getEmployeeDashboard(Integer empId) {
        List<Person> person = personRepository.findPersonByEmpMasterId(empId);
        return getEmployeeDashboardVOList(person);
    }

//    public List<EmployeeDashboardVO> getEmployeeDashboardVOListForSchedule(List<EmployeeVaccAppointmentInfo> employeeVaccAppointmentInfoList, EmpVaccAppointmentVO empVaccAppointmentVO) {
//        List<EmployeeDashboardVO> voList = new ArrayList<>();
//        for(EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo : employeeVaccAppointmentInfoList) {
//            Person person = personRepository.findByPersonId(employeeVaccAppointmentInfo.getPersonId());
//            EmployeeDashboardVO employeeDashboardVO = getEmployeeDashboardVO(person);
//
//        }
//
//    }

    private List<EmployeeDashboardVO> getEmployeeDashboardVOList(List<Person> personList) {
        List<EmployeeDashboardVO> voList = new ArrayList<>();
        for (Person person : personList) {
            voList.add(getEmployeeDashboardVO(person));
        }
        return voList;
    }

    private EmployeeDashboardVO getEmployeeDashboardVO(Person person) {
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
        return employeeDashboardVO;
    }


}
