package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.*;
import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;
import com.harman.ebook.vaccination.covid.entity.Lov;
import com.harman.ebook.vaccination.covid.entity.Person;
import com.harman.ebook.vaccination.covid.repository.EmpMasterRespository;
import com.harman.ebook.vaccination.covid.repository.EmployeeVaccAppointmentInfoRepository;
import com.harman.ebook.vaccination.covid.repository.LovRepository;
import com.harman.ebook.vaccination.covid.repository.PersonRespository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import static com.harman.ebook.vaccination.covid.constants.LovConstants.*;

@Service
public class EmployeeService {
    @Autowired
    EmpMasterRespository emprepos;

    @Autowired
    PersonRespository personRepository;

    @Autowired
    EmpMasterRespository empMasterRespository;

    @Autowired
    EmployeeVaccAppointmentInfoRepository employeeVaccSchInfoRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private ApplicationResponseService appResponseService;

    @Autowired
    private LovRepository lovRepository;

    public List<EmployeeMaster> getEmployee() {
        return emprepos.findAll();
    }


    /**
     *
     * @param empId
     * @return GenericResponseEnity - List of EmployeeDashboardVO
     */
    public GenericResponseEntity getEmployeeDashboardResponse(Integer empId) {
        DashboardResponseVO dashboardresVO = new DashboardResponseVO();
        EmployeeMaster employeeMaster = empMasterRespository.findById(empId).orElseThrow(null);
        List<EmployeeDashboardVO> employeeDashboardVOList = getEmployeeDashboard(empId);
        EmployeeVO employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employeeMaster,employeeVO);
        dashboardresVO.setEmployeeMaster(employeeVO);
        dashboardresVO.setEmployeeDashboardVOS(employeeDashboardVOList);
        return appResponseService.genSuccessResponse(VaccinationConstants.RECORD_FOUNDS, dashboardresVO);
    }

    /**
     *
     * @param empId
     * @return List of EmployeeDashboardVO
     */
    public List<EmployeeDashboardVO> getEmployeeDashboard(Integer empId) {
        List<Person> person = personRepository.findPersonByEmpMasterIdOrderByPersonId(empId);
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
        employeeDashboardVO.setCowinid(person.getCowinid());
        employeeDashboardVO.setManipalid(person.getManipalid());
        employeeDashboardVO.setDateOfDoseI (person.getDateOfDoseI());
        if(ObjectUtils.isEmpty(person.getCowinid()) ){
            employeeDashboardVO.setIsRegistered(false);
        }else {
            employeeDashboardVO.setIsRegistered(true);
        }
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
               .setDateOfVaccination(DateUtil.getDateString(employeeVaccAppointmentInfo.getDateOfVaccination()));
           empVaccAppointmentVO.setLocation(employeeVaccAppointmentInfo.getLocation());
       }

        return empVaccAppointmentVO;
    }

    /**
     *
     * @param personDTO
     * @param empId
     * @return
     */
    public GenericResponseEntity registerEmployee(PersonRegisterDTO personDTO,
        Integer empId) {
        Person person = personRepository.findById(personDTO.getPersonId()).orElse(null);
        if(!ObjectUtils.isEmpty(person)){
                person.setCowinid(personDTO.getCowinid());
                person.setManipalid(personDTO.getManipalid());
            if(!ObjectUtils.isEmpty(personDTO.getCowinid())){
                person.setIsRegistered(true);
            }else {
                person.setIsRegistered(false);
            }
            person.setDateOfDoseI(personDTO.getDateOfDoseI());
            personRepository.save(person);
        }

        return employeeService.getEmployeeDashboardResponse(empId);
    }
}
