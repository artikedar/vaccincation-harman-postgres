package com.harman.ebook.vaccination.covid.controllers;

import com.harman.ebook.vaccination.covid.domain.VaccineInventorySchedule;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.service.VaccineInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "${spring.data.rest.basePath}", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VaccineInventoryController {

    @Autowired
    private VaccineInventoryService vaccineInventoryService;

    @PostMapping(value = "/api/vaccine/inventory")
    public GenericResponseEntity insertIntoVaccineInventory(@RequestBody VaccineInventorySchedule vacInvSchedule) throws ParseException {
        return vaccineInventoryService.insertIntoVaccineInventory(vacInvSchedule);
    }
}
