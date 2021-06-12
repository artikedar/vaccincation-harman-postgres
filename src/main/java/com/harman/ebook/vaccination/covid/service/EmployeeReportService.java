package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.EmployeeReportVO;
import com.harman.ebook.vaccination.covid.domain.PersonAppointmentVO;
import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;
import com.harman.ebook.vaccination.covid.entity.Lov;
import com.harman.ebook.vaccination.covid.entity.Person;
import com.harman.ebook.vaccination.covid.repository.EmployeeVaccAppointmentInfoRepository;
import com.harman.ebook.vaccination.covid.repository.LovRepository;
import com.harman.ebook.vaccination.covid.repository.PersonRespository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.harman.ebook.vaccination.covid.constants.LovConstants.LOV_TYPE_STATUS;

@Service
@Slf4j
public class EmployeeReportService {

    @Autowired
    private EmployeeVaccAppointmentInfoRepository employeeVaccSchInfoRepository;

    @Autowired
    private PersonRespository personRespository;

    @Autowired
    private ApplicationResponseService appResponseService;

    @Autowired
    private LovRepository lovRepository;

    public GenericResponseEntity getEmployeeReport(Short location, String bookingDate, Short appointmentstatus) {
        Date dateOfVaccination = DateUtil.getDate(bookingDate);
        List<EmployeeVaccAppointmentInfo> employeeVaccAppointmentInfoList = employeeVaccSchInfoRepository.findEmployeeVaccAppointmentInfosByLocationAndDateOfVaccinationAndStatus(location, dateOfVaccination, appointmentstatus);

        EmployeeReportVO employeeReportVO = new EmployeeReportVO();
        employeeReportVO.setLocation(location);
        employeeReportVO.setDateOfVaccination(dateOfVaccination);
        List<PersonAppointmentVO> personVoList = new ArrayList<>();
        List<Lov> lovList = lovRepository.getLovByLovtypeIdIsActive(LOV_TYPE_STATUS, Boolean.TRUE);

        for(EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo : employeeVaccAppointmentInfoList) {
            Person person = personRespository.findPersonByPersonId(employeeVaccAppointmentInfo.getPersonId());
            PersonAppointmentVO personVO = new PersonAppointmentVO();
            personVO.setPersonId(employeeVaccAppointmentInfo.getPersonId());
            personVO.setFullName(person.getFullName());
            personVO.setSlotNo(employeeVaccAppointmentInfo.getSlotNo());
            personVO.setEmpMasterId(person.getEmpMasterId());
            personVO.setEmpVaccAppId(employeeVaccAppointmentInfo.getEmpVaccAppId());
            Lov lov = lovList.stream().filter(l -> l.getValueid() == employeeVaccAppointmentInfo.getSlotNo().intValue()).findFirst().orElse(null);
            if(!ObjectUtils.isEmpty(lov)) {
                personVO.setSlotName(lov.getValue());
            }
            personVoList.add(personVO);
        }
        employeeReportVO.setPersonList(personVoList);
        return appResponseService.genSuccessResponse(VaccinationConstants.STATUS_FLAG_SUCCESS, employeeReportVO);
    }
}
