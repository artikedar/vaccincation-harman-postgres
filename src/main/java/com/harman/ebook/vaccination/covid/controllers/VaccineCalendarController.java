package com.harman.ebook.vaccination.covid.controllers;

import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.service.VaccineCalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "${spring.data.rest.basePath}", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VaccineCalendarController {

    @Autowired
    private VaccineCalendarService vaccineCalendarService;

    /**
     *
     * @param fromDate
     * @param location
     * @return Next five days of vaccine availability
     */
    @GetMapping (value = "/api/vaccine/calendar/location/{location}")
    public GenericResponseEntity getVaccineCalendar(@RequestParam(name = "fromDate",required = true) String fromDate, @PathVariable(name = "location",required = true) Short location) throws ParseException {
        return vaccineCalendarService.getVaccineCalendarVO(fromDate, location);
    }
}
