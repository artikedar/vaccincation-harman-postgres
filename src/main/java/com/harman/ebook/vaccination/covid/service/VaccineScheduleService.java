package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.AppointmentRequest;
import com.harman.ebook.vaccination.covid.domain.DashboardResponseVO;
import com.harman.ebook.vaccination.covid.domain.EmpVaccAppointmentVO;
import com.harman.ebook.vaccination.covid.domain.EmployeeDashboardVO;
import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;
import com.harman.ebook.vaccination.covid.repository.EmployeeVaccSchInfoRepository;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class VaccineScheduleService {

    @Autowired
    private EmployeeVaccSchInfoRepository employeeVaccSchInfoRepository;

    @Autowired
    private EmployeeService employeeService;

    /**
     *
     * @param req : AppointmentRequest
     * @return returns List<EmployeeDashboardVO> as response
     */
    @Transactional
    public GenericResponseEntity scheduleVaccine(AppointmentRequest req) {
        List<EmployeeVaccAppointmentInfo> appointmentInfoList = new ArrayList<>();

        for(Integer personId : req.getPersonIds()) {
            Short status = 1;
            EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo = getEmpolyeeVaccSchInfo(req, personId, status);
            appointmentInfoList.add(employeeVaccAppointmentInfo);
        }
        employeeVaccSchInfoRepository.saveAll(appointmentInfoList);
        return employeeService.getEmployeeDashboardResponse(req.getEmpMasterId());
    }


//    private EmpVaccAppointmentVO getEmpVaccAppointmentVO(EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo) {
//        EmpVaccAppointmentVO empVaccAppointmentVO = new EmpVaccAppointmentVO();
//        empVaccAppointmentVO.setEmpVaccSchId(employeeVaccAppointmentInfo.getEmpVaccSchId());
//        empVaccAppointmentVO.setDateOfVaccination(empVaccAppointmentVO.getDateOfVaccination());
//        empVaccAppointmentVO.setVacType(employeeVaccAppointmentInfo.getVacType());
//        empVaccAppointmentVO.setLocation(employeeVaccAppointmentInfo.getLocation());
//        empVaccAppointmentVO.setPersonId(employeeVaccAppointmentInfo.getPersonId());
//        empVaccAppointmentVO.setSlotNo(employeeVaccAppointmentInfo.getSlotNo());
//        empVaccAppointmentVO.set
//    }

    private EmployeeVaccAppointmentInfo getEmpolyeeVaccSchInfo(AppointmentRequest req, Integer personId, Short status) {
        EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo = new EmployeeVaccAppointmentInfo();
        employeeVaccAppointmentInfo.setLocation(req.getLocation());
        employeeVaccAppointmentInfo.setDateOfVaccination(DateUtil.getDate(req.getBookingDate()));
        employeeVaccAppointmentInfo.setIsBookingActive(Boolean.TRUE);
        employeeVaccAppointmentInfo.setVacType(req.getVacType());
        employeeVaccAppointmentInfo.setSlotNo(req.getSlotNo());
        employeeVaccAppointmentInfo.setPersonId(personId);
        employeeVaccAppointmentInfo.setStatus(status);
        Short dose = 1;
        employeeVaccAppointmentInfo.setDoseLevel(dose);
        return employeeVaccAppointmentInfo;
    }
}
