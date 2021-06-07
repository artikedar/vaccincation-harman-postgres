package com.harman.ebook.vaccination.covid.service;

import static com.harman.ebook.vaccination.covid.constants.LovConstants.LOV_APP_STATUS_BOOKED;
import static com.harman.ebook.vaccination.covid.constants.LovConstants.LOV_APP_STATUS_CANCELED;

import com.harman.ebook.vaccination.covid.domain.AppointmentRequest;
import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;
import com.harman.ebook.vaccination.covid.repository.EmployeeVaccSchInfoRepository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class VaccineScheduleService {

    @Autowired
    private EmployeeVaccSchInfoRepository employeeVaccSchInfoRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private VaccineInventoryService vaccineInventoryService;

    @Autowired
    private ApplicationResponseService applicationResponseService;

    /**
     * set the status appointment status to booked = 1
     * @param req : AppointmentRequest
     * @return returns List<EmployeeDashboardVO> as response
     */
    @Transactional
    public GenericResponseEntity scheduleVaccine(AppointmentRequest req) {

        //update the status to cancel for exsisting appointment
        for(Integer appointmentNo: req.getEmpVaccAppIds()){
            boolean isCancel = cancelVaccine(appointmentNo);
            if(!isCancel){
               return applicationResponseService.genFailureResponse("Appointment not valid to cancel ","");
            }
        }
        //schedule new appointment for all person in req payalod
        List<EmployeeVaccAppointmentInfo> appointmentInfoList = new ArrayList<>();
        for(Integer personId : req.getPersonIds()) {
            boolean isValidBooking = validateBooking(personId);
            if(!isValidBooking){
                return applicationResponseService.genFailureResponse("Please review booked appointments ","");
            }
            EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo = getEmpolyeeVaccSchInfo(req, personId, LOV_APP_STATUS_BOOKED, Boolean.TRUE);
            appointmentInfoList.add(employeeVaccAppointmentInfo);
        }
        appointmentInfoList = employeeVaccSchInfoRepository.saveAll(appointmentInfoList);

        //update slots into vaccine inventory
        for( EmployeeVaccAppointmentInfo appInfo:appointmentInfoList) {
            vaccineInventoryService.updateDoseAvailability(appInfo);
        }

        //return the dashbaord response vo
        return employeeService.getEmployeeDashboardResponse(req.getEmpMasterId());
    }



    /**
     * set the status appointment status to booked = 1
     * @param req : AppointmentRequest
     * @return returns List<EmployeeDashboardVO> as response
     */
    @Transactional
    public GenericResponseEntity cancelScheduledVaccine(Integer appointmentId,Integer empMasterId) {

        boolean isCancel = cancelVaccine(appointmentId);
        if(!isCancel){
           return applicationResponseService.genFailureResponse("Appointment not valid to cancel ","");
        }
        return employeeService.getEmployeeDashboardResponse(empMasterId);
    }

    /**
     * set the status appointment status to cancelled = 2
     * @param req
     * @return returns List<EmployeeDashboardVO> as response
     */
    @Transactional
    public boolean cancelVaccine(Integer appointmentId ) {
        EmployeeVaccAppointmentInfo appointmentInfo = employeeVaccSchInfoRepository.findById(appointmentId).orElse(null);
       // check if appointment present or not booked
        if(ObjectUtils.isEmpty(appointmentInfo) ||
            appointmentInfo.getStatus().shortValue()!=LOV_APP_STATUS_BOOKED){
            return false;
        }
        if(!ObjectUtils.isEmpty(appointmentInfo)){
            appointmentInfo.setStatus(LOV_APP_STATUS_CANCELED);
            appointmentInfo = employeeVaccSchInfoRepository.save(appointmentInfo);

            //update slots into vaccine inventory
            vaccineInventoryService.updateDoseAvailability(appointmentInfo);

            return true;
        }
        return false;
    }

    @Transactional
    public boolean validateBooking(Integer personId ) {
        boolean isBookingValid = true;
        EmployeeVaccAppointmentInfo appointmentInfo = employeeVaccSchInfoRepository.findEmployeeVaccAppointmentInfoByPersonIdAndStatus(personId, LOV_APP_STATUS_BOOKED);

        if(ObjectUtils.isEmpty(appointmentInfo) ||
            appointmentInfo.getStatus().shortValue()==LOV_APP_STATUS_BOOKED){
            isBookingValid = false;
        }
        return isBookingValid;
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
