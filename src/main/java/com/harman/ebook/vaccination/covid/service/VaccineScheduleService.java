package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.domain.AppointmentRequest;
import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;
import com.harman.ebook.vaccination.covid.entity.Person;
import com.harman.ebook.vaccination.covid.repository.EmployeeVaccAppointmentInfoRepository;
import com.harman.ebook.vaccination.covid.repository.PersonRespository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.harman.ebook.vaccination.covid.constants.LovConstants.*;

@Service
public class VaccineScheduleService {

    @Autowired
    private EmployeeVaccAppointmentInfoRepository employeeVaccSchInfoRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private VaccineInventoryService vaccineInventoryService;

    @Autowired
    private ApplicationResponseService applicationResponseService;

    @Autowired
    private PersonRespository personRespository;

    /**
     * set the status appointment status to booked = 1
     * @param appointmentRequest : AppointmentRequest
     * @return returns List<EmployeeDashboardVO> as response
     */
    @Transactional
    public GenericResponseEntity scheduleVaccine(AppointmentRequest appointmentRequest) {

        //check number of booking count
        boolean isValidBookingCount = checkValidBookingCount(appointmentRequest);
        if(!isValidBookingCount) {
            return applicationResponseService.genFailureResponse("No sufficient slots available","");
        }

        //update the status to cancel for exsisting appointment
        for(Integer appointmentNo: appointmentRequest.getEmpVaccAppIds()){
            boolean isCancel = cancelVaccine(appointmentNo);
            if(!isCancel){
               return applicationResponseService.genFailureResponse("Appointment not valid to cancel ","");
            }
        }

        // check if person already has booking
        for(Integer personId : appointmentRequest.getPersonIds()) {
            boolean isValidBooking = validateBooking(personId);
            if (!isValidBooking) {
                return applicationResponseService
                    .genFailureResponse("Please review booked appointments ", "");
            }

            Person person = personRespository.findById(personId).orElse(null);
            if (ObjectUtils.isEmpty(person)) {
                return applicationResponseService
                        .genFailureResponse("Person with id " + personId + " does not exists.", personId);
            }
            boolean isValidSecondDose = validateSecondDosePeriod(person, appointmentRequest.getBookingDate());
            if (!isValidSecondDose) {
                return applicationResponseService
                        .genFailureResponse("Gap between two doses of vaccine should not be less than 84 days.", "");
            }
        }

        //schedule new appointment for all person in req payalod
        List<EmployeeVaccAppointmentInfo> appointmentInfoList = new ArrayList<>();
        for(Integer personId : appointmentRequest.getPersonIds()) {
            EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo = getEmpolyeeVaccSchInfo(appointmentRequest, personId, LOV_APP_STATUS_BOOKED, Boolean.TRUE);
            appointmentInfoList.add(employeeVaccAppointmentInfo);
        }
        appointmentInfoList = employeeVaccSchInfoRepository.saveAll(appointmentInfoList);

        //update slots into vaccine inventory
        for( EmployeeVaccAppointmentInfo appInfo:appointmentInfoList) {
            vaccineInventoryService.updateDoseAvailability(appInfo);
        }

        //return the dashbaord response vo
        return employeeService.getEmployeeDashboardResponse(appointmentRequest.getEmpMasterId());
    }

    private boolean validateSecondDosePeriod(Person person, String bookingDate) {
        Date dateOfDoseI = person.getDateOfDoseI();
        if (ObjectUtils.isEmpty(dateOfDoseI)) {
            return true;
        }
        Date appBookingDt = DateUtil.getDate(bookingDate);
        System.out.println("Days Diff between two doses for Person : " + person.getPersonId() + ", " + person.getFullName() + " is " + DateUtil.daysDiff(appBookingDt, dateOfDoseI));
        if (DateUtil.daysDiff(appBookingDt, dateOfDoseI) < 84) {
            return false;
        }
        return true;
    }

    /**
     * set the status appointment status to booked = 1
     * @param appointmentId
     * @param empMasterId
     * @return
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
     * @param appointmentId
     * @return
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
        boolean isBookingValid = false;
        EmployeeVaccAppointmentInfo appointmentInfo = employeeVaccSchInfoRepository.findEmployeeVaccAppointmentInfoByPersonIdAndStatus(personId, LOV_APP_STATUS_BOOKED);

        if(ObjectUtils.isEmpty(appointmentInfo)){
            isBookingValid = true;
        }else{
            if( appointmentInfo.getStatus().shortValue()==LOV_APP_STATUS_BOOKED ||
            appointmentInfo.getStatus().shortValue()==LOV_APP_STATUS_COMPLETED){
                isBookingValid = false;
            }else if (appointmentInfo.getStatus().shortValue()==LOV_APP_STATUS_CANCELED ) {
                isBookingValid = true;
            }
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

    private Boolean checkValidBookingCount(AppointmentRequest req) {
        return vaccineInventoryService.checkValidBookingCount(req);
    }


    /**
     *
     * @param appointmentId
     * @return
     */
    public GenericResponseEntity hrCancelHRScheduledVaccine(Integer appointmentId) {
        boolean isCancel = cancelVaccine(appointmentId);
        if(!isCancel){
            return applicationResponseService.genFailureResponse("Appointment not valid to cancel ","");
        }
        return applicationResponseService.genSuccessResponse("Appointment cancelled by HR ","");
    }
}
