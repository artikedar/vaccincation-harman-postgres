package com.harman.ebook.vaccination.covid.controllers;

import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.service.EmployeeImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "${spring.data.rest.basePath}", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class EmployeeImportController {

    @Autowired
    private EmployeeImportService employeeImportService;

    /**
     *
     * @param file
     * @param isDependant
     * @return
     */
    @PostMapping(value = "/api/vaccine/employee/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseEntity submit(@RequestParam("file") MultipartFile file,
        @RequestParam(name = "isDependant",required = true) Boolean isDependant) {
        return employeeImportService.importSupplier(file,isDependant);
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/api/vaccine/employee/export/sample", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseEntity exportEmployeeSample() {
        return employeeImportService.exportEmployeeSample();
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/api/vaccine/dependant/export/sample", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseEntity exportDependantSample() {
        return employeeImportService.exportEmployeeSample();
    }

}
