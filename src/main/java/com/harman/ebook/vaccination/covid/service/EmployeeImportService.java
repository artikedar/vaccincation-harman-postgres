package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.csvparser.CSVParser;
import com.harman.ebook.vaccination.covid.repository.EmpMasterRespository;
import com.harman.ebook.vaccination.covid.repository.PersonRespository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Bhagwat Rokade
 * @project Ingestion
 * @created 6/7/2021
 */
@Service
@Slf4j
public class EmployeeImportService {

  @Autowired
  ApplicationResponseService appResponseService;

  @Autowired
  EmpMasterRespository empMasterRespository;

  @Autowired
  PersonRespository personRespository;

  /**
   *
   * @param file
   * @param isDependant
   * @return
   */
  public GenericResponseEntity importSupplier(MultipartFile file, Boolean isDependant) {
    CSVParser  csvParser = new CSVParser();
    csvParser.parseFile(file);
//    if (isDependant)
//        return appResponseService.genSuccessResponse("",empMasterRespository.findAll());
//    else
//      return appResponseService.genSuccessResponse("",personRespository.findAll());

        return appResponseService.genSuccessResponse("","data imported successfully");

  }

  /**
   *
   * @return
   */
  public GenericResponseEntity exportEmployeeSample() {
    return appResponseService.genSuccessResponse("Sample employee file",
        "EmployeeNo,FullName,GRADE,DOJ,workplace,DOB,Email,Gender,EmploymentType,Country\n"
            + "1111,asdf,91,12/15/2020,Pune,10/20/1999,abc@harman.com,M,2,India");
  }

  /**
   *
   * @return
   */
  public GenericResponseEntity exportDependantSample() {
    return appResponseService.genSuccessResponse("Sample employee file",
        "Sr. no,EMPLOYEE CODE,NAME,RELATION,AGE,GENDER,OFFICAL EMAIL ID,LOCATION\n"
            + "1,1005,EFGH1,Self,44,M,EFGH1@harman.com,Bangalore (Eminence)");
  }


}
