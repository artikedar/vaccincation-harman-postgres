package com.harman.ebook.vaccination.covid.controllers;

import com.harman.ebook.vaccination.covid.domain.AppointmentRequest;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.service.VaccineScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "${spring.data.rest.basePath}", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VaccineScheduleController {

    @Autowired
    private VaccineScheduleService vaccineScheduleService;

    /**
     * Schedules the appointment for vaccination
     * @param req
     * @return
     */
    @PostMapping("/api/appointment/schedule")
    public GenericResponseEntity scheduleVaccine(@RequestBody AppointmentRequest req) {
        return vaccineScheduleService.scheduleVaccine(req);
    }

    /**
     * Cancels the appointment for vaccination
     * @param req
     * @return
     */
    @PostMapping("/api/appointment/cancel")
    public GenericResponseEntity cancelScheduledVaccine(@RequestParam(name = "empVaccAppId") Integer empVaccAppId,
        @RequestParam(name = "empMasterId") Integer empMasterId) {
        return vaccineScheduleService.cancelScheduledVaccine(empVaccAppId,empMasterId);
    }
}
