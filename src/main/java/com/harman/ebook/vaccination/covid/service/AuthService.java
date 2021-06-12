package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
import com.harman.ebook.vaccination.covid.repository.EmpMasterRespository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.util.DateUtil;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class AuthService {

  @Autowired
  EmployeeService empService;

  @Autowired
  ApplicationResponseService appResponseService;

  @Autowired
  EmployeeService employeeService;

  @Autowired
  EmpMasterRespository empMasterRespository;

  /**
   * @param empId
   * @param doj
   * @param request
   * @return
   * @throws Exception
   */
  public GenericResponseEntity validateUser(String empId, String doj, HttpServletRequest request) {
    Date reqDoj = null;

    reqDoj = DateUtil.formatDate(doj);
    if (ObjectUtils.isEmpty(reqDoj)) {
      appResponseService.genFailureResponse("Date format not valid", "");
    }
    EmployeeMaster empInfo = empMasterRespository.findByEmployeeId(empId);
    if (null != empInfo && empInfo.getIsactive()) {
      String strDate = empInfo.getDob().toString().substring(0, 10);
      Date dbDoj = DateUtil.formatDate(strDate);
      if (!ObjectUtils.isEmpty(reqDoj) && reqDoj.equals(dbDoj)) {
//        HttpSession session = request.getSession(true);
//        session.setMaxInactiveInterval(190);
//        session.setAttribute("EMPLOYEE_ID", empId);
        return employeeService.getEmployeeDashboardResponse(empInfo.getEmpMasterId());
      }
    }
    return appResponseService.genFailureResponse(VaccinationConstants.INVALID_USER, null);

  }
}
