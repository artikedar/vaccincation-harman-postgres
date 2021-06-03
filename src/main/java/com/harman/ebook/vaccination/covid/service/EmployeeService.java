package com.harman.ebook.vaccination.covid.service;

import java.util.ArrayList;
import java.util.List;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.EmpVaccAppointmentVO;
import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;
import com.harman.ebook.vaccination.covid.repository.EmployeeVaccSchInfoRepository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harman.ebook.vaccination.covid.domain.EmployeeDashboardVO;
import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
import com.harman.ebook.vaccination.covid.entity.Person;
import com.harman.ebook.vaccination.covid.repository.MasterEmpRespository;
import com.harman.ebook.vaccination.covid.repository.PersonRespository;
import org.springframework.util.ObjectUtils;

import static com.harman.ebook.vaccination.covid.constants.LovConstants.LOV_APP_STATUS_BOOKED;

@Service
public class EmployeeService {
    @Autowired
    MasterEmpRespository emprepos;

    @Autowired
    PersonRespository personRepository;

    @Autowired
    EmployeeVaccSchInfoRepository employeeVaccSchInfoRepository;

    @Autowired
    private ApplicationResponseService appResponseService;

    public List<EmployeeMaster> getEmployee() {
        return emprepos.findAll();
    }

    /**
     *
     * @param id
     * @return Empolyee data for given id
     */
    public EmployeeMaster findByEmployeeId(Integer id) {
        EmployeeMaster employeeRec = emprepos.findByEmployeeId(id);
        return employeeRec;
    }

    /**
     *
     * @param empId
     * @return GenericResponseEnity - List of EmployeeDashboardVO
     */
    public GenericResponseEntity getEmployeeDashboardResponse(Integer empId) {
        List<EmployeeDashboardVO> vo = getEmployeeDashboard(empId);
        return appResponseService.genSuccessResponse(VaccinationConstants.RECORD_FOUNDS, vo);
    }

    /**
     *
     * @param empId
     * @return List of EmployeeDashboardVO
     */
    public List<EmployeeDashboardVO> getEmployeeDashboard(Integer empId) {
        List<Person> person = personRepository.findPersonByEmpMasterId(empId);
        return getEmployeeDashboardVOList(person);
    }

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
        employeeDashboardVO.setEmpVaccAppointmentVO(getEmpVaccAppointmentVO(person.getPersonId()));
        return employeeDashboardVO;
    }

    /**
     *
     * @param personId
     * @return
     */
    private EmpVaccAppointmentVO getEmpVaccAppointmentVO(Integer personId) {
        EmpVaccAppointmentVO empVaccAppointmentVO = new EmpVaccAppointmentVO();
        EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo = employeeVaccSchInfoRepository.findEmployeeVaccAppointmentInfoByPersonIdAndStatus(personId, LOV_APP_STATUS_BOOKED);
       if(!ObjectUtils.isEmpty(employeeVaccAppointmentInfo)) {
           empVaccAppointmentVO.setEmpVaccAppId(employeeVaccAppointmentInfo.getEmpVaccAppId());
           empVaccAppointmentVO.setSlotNo(employeeVaccAppointmentInfo.getSlotNo());
           empVaccAppointmentVO
               .setDateOfVaccination(employeeVaccAppointmentInfo.getDateOfVaccination());
       } else {return null;}

        return empVaccAppointmentVO;
    }
}
