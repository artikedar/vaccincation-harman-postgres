package com.harman.ebook.vaccination.covid.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.EmployeeReportVO;
import com.harman.ebook.vaccination.covid.domain.PersonAppointmentVO;
import com.harman.ebook.vaccination.covid.domain.PersonCsvVO;
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
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.harman.ebook.vaccination.covid.constants.LovConstants.LOV_TYPE_LOCATION;
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

    public GenericResponseEntity getEmployeeReport(Short location, String bookingDate, Short appointmentstatus) throws IOException {
        Date dateOfVaccination = DateUtil.getDate(bookingDate);
        EmployeeReportVO employeeReportVO = new EmployeeReportVO();
        employeeReportVO.setLocation(location);
        employeeReportVO.setDateOfVaccination(DateUtil.getDateString(dateOfVaccination));
        List<PersonAppointmentVO> personVoList = getPersonVoList(location, bookingDate, appointmentstatus);
        employeeReportVO.setPersonList(personVoList);
        return appResponseService.genSuccessResponse(VaccinationConstants.STATUS_FLAG_SUCCESS, employeeReportVO);
    }

    public void getEmployeeReport(Short location, String bookingDate, Short appointmentstatus, HttpServletResponse response) throws IOException, ParseException {
        List<PersonAppointmentVO> personVoList = getPersonVoList(location, bookingDate, appointmentstatus);
        List<PersonCsvVO> personCsvVoList = new ArrayList<>();
        List<Lov> lovLocationList = lovRepository.getLovByLovtypeIdIsActive(LOV_TYPE_LOCATION, Boolean.TRUE);
        Lov lov = lovLocationList.stream().filter(l -> l.getValueid() == location.intValue()).findFirst().orElse(null);
        String locationName = null;
        if (!ObjectUtils.isEmpty(lov)) {
            locationName = lov.getValue();
        }
        for (PersonAppointmentVO personAppointmentVO : personVoList) {
            PersonCsvVO personCsvVO = new PersonCsvVO();
            personCsvVO.setPersonId(personAppointmentVO.getPersonId());
            personCsvVO.setEmpMasterId(personAppointmentVO.getEmpMasterId());
            personCsvVO.setEmpVaccAppId(personAppointmentVO.getEmpVaccAppId());
            personCsvVO.setFullName(personAppointmentVO.getFullName());
            personCsvVO.setSlotNo(personAppointmentVO.getSlotNo());
            personCsvVO.setSlotName(personAppointmentVO.getSlotName());
            personCsvVO.setLocationName(locationName);
            personCsvVO.setDateOfVaccination(bookingDate);
            personCsvVoList.add(personCsvVO);
        }
        String fileName = "harman-vaccination-report-" + System.currentTimeMillis() +".csv";
        FileWriter writer = new FileWriter("D:\\temp\\" + fileName);
        String str = "PersonId,EmpMasterId,EmpVaccAppId,FullName,SlotNo,SlotName,LocationName,DateOfVaccination";
        writer.write(str);
        writer.write("\n");
        personCsvVoList.forEach(vo -> {
                    try {
                        writer.write(vo.toString());
                        writer.write("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        writer.close();

        FileInputStream fileInputStream = new FileInputStream("D:\\temp\\" + fileName);
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        response.getWriter().print(IOUtils.toString(fileInputStream, StandardCharsets.UTF_8));
//        return appResponseService.genSuccessResponse(VaccinationConstants.STATUS_FLAG_SUCCESS, response);
    }

    private List<PersonAppointmentVO> getPersonVoList(Short location, String bookingDate, Short appointmentstatus) {
        Date dateOfVaccination = DateUtil.getDate(bookingDate);
        List<EmployeeVaccAppointmentInfo> employeeVaccAppointmentInfoList = employeeVaccSchInfoRepository.findEmployeeVaccAppointmentInfosByLocationAndDateOfVaccinationAndStatus(location, dateOfVaccination, appointmentstatus);

        List<PersonAppointmentVO> personVoList = new ArrayList<>();
        List<Lov> lovList = lovRepository.getLovByLovtypeIdIsActive(LOV_TYPE_STATUS, Boolean.TRUE);

        for (EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo : employeeVaccAppointmentInfoList) {
            Person person = personRespository.findPersonByPersonId(employeeVaccAppointmentInfo.getPersonId());
            PersonAppointmentVO personVO = new PersonAppointmentVO();
            personVO.setPersonId(employeeVaccAppointmentInfo.getPersonId());
            personVO.setFullName(person.getFullName());
            personVO.setSlotNo(employeeVaccAppointmentInfo.getSlotNo());
            personVO.setEmpMasterId(person.getEmpMasterId());
            personVO.setEmpVaccAppId(employeeVaccAppointmentInfo.getEmpVaccAppId());
            Lov lov = lovList.stream().filter(l -> l.getValueid() == employeeVaccAppointmentInfo.getSlotNo().intValue()).findFirst().orElse(null);
            if (!ObjectUtils.isEmpty(lov)) {
                personVO.setSlotName(lov.getValue());
            }
            personVoList.add(personVO);
        }
        return personVoList;
    }
}
