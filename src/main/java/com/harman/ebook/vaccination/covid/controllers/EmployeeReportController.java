package com.harman.ebook.vaccination.covid.controllers;

import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.service.EmployeeReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "${spring.data.rest.basePath}", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class EmployeeReportController {

    @Autowired
    private EmployeeReportService employeeReportService;

    /**
     *
     * @param location
     * @param bookingDate
     * @param appointmentstatus
     * @return list of persons
     */
    @GetMapping(value = "/api/vaccine/report/daywise")
    public GenericResponseEntity getEmployeeReport(@RequestParam(name = "location") Short location,
                                                   @RequestParam(name = "bookingDate") String bookingDate,
                                                   @RequestParam(name = "appointmentstatus") Short appointmentstatus) {
        return employeeReportService.getEmployeeReport(location, bookingDate, appointmentstatus);
    }
}