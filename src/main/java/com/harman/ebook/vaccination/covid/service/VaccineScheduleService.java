package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.domain.AppointmentRequest;
import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;
import com.harman.ebook.vaccination.covid.repository.EmployeeVaccSchInfoRepository;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.harman.ebook.vaccination.covid.constants.LovConstants.LOV_APP_STATUS_BOOKED;
import static com.harman.ebook.vaccination.covid.constants.LovConstants.LOV_APP_STATUS_CANCELED;

@Service
public class VaccineScheduleService {

    @Autowired
    private EmployeeVaccSchInfoRepository employeeVaccSchInfoRepository;

    @Autowired
    private EmployeeService employeeService;

    /**
     * set the status appointment status to booked = 1
     * @param req : AppointmentRequest
     * @return returns List<EmployeeDashboardVO> as response
     */
    @Transactional
    public GenericResponseEntity scheduleVaccine(AppointmentRequest req) {
        List<EmployeeVaccAppointmentInfo> appointmentInfoList = new ArrayList<>();
        for(Integer personId : req.getPersonIds()) {
            EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo = getEmpolyeeVaccSchInfo(req, personId, LOV_APP_STATUS_BOOKED, Boolean.TRUE);
            appointmentInfoList.add(employeeVaccAppointmentInfo);
        }
        employeeVaccSchInfoRepository.saveAll(appointmentInfoList);
        return employeeService.getEmployeeDashboardResponse(req.getEmpMasterId());
    }

    /**
     * set the status appointment status to cancelled = 2
     * @param req
     * @return returns List<EmployeeDashboardVO> as response
     */
    @Transactional
    public GenericResponseEntity cancelVaccine(AppointmentRequest req) {
        List<EmployeeVaccAppointmentInfo> appointmentInfoList = new ArrayList<>();
        for(Integer personId : req.getPersonIds()) {
            EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo = getEmpolyeeVaccSchInfo(req, personId, LOV_APP_STATUS_CANCELED, Boolean.FALSE);
            appointmentInfoList.add(employeeVaccAppointmentInfo);
        }
        employeeVaccSchInfoRepository.saveAll(appointmentInfoList);
        return employeeService.getEmployeeDashboardResponse(req.getEmpMasterId());
    }

    private EmployeeVaccAppointmentInfo getEmpolyeeVaccSchInfo(AppointmentRequest req, Integer personId, Short status, Boolean bookingStatus) {
        EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo = new EmployeeVaccAppointmentInfo();
        employeeVaccAppointmentInfo.setLocation(req.getLocation());
        employeeVaccAppointmentInfo.setDateOfVaccination(DateUtil.getDate(req.getBookingDate()));
        employeeVaccAppointmentInfo.setIsBookingActive(bookingStatus);
        employeeVaccAppointmentInfo.setVacType(req.getVacType());
        employeeVaccAppointmentInfo.setSlotNo(req.getSlotNo());
        employeeVaccAppointmentInfo.setPersonId(personId);
        employeeVaccAppointmentInfo.setStatus(status);
        Short dose = 1;
        employeeVaccAppointmentInfo.setDoseLevel(dose);
        return employeeVaccAppointmentInfo;
    }
}
