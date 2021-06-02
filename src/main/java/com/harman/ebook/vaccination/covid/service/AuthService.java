package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.DashboardResponseVO;
import com.harman.ebook.vaccination.covid.domain.EmployeeDashboardVO;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;

@Service
public class AuthService {

	@Autowired
	EmployeeService empService;

	@Autowired
	ApplicationResponseService appResponseService;

	@Autowired
	EmployeeService employeeService;

	/**
	 * @param empId
	 * @param doj
	 * @return
	 * @throws Exception
	 */
	public GenericResponseEntity validateUser(Integer empId, String doj) throws Exception {
		EmployeeMaster empInfo = null;
		Date reqDoj = new SimpleDateFormat("yyyy-dd-mm").parse(doj);

		empInfo = empService.findByEmployeeId(empId);
		if (null != empInfo && empInfo.getIsactive()) {
			String strDate = empInfo.getDateOfJoining().toString().substring(0, 10);
			Date dbDoj = new SimpleDateFormat("yyyy-dd-mm").parse(strDate);
			if (reqDoj.equals(dbDoj)) {
				DashboardResponseVO dashboardresVO = new DashboardResponseVO();
				List<EmployeeDashboardVO> employeeDashboardVOList = employeeService.getEmployeeDashboard(empInfo.getEmpMasterId());
				dashboardresVO.setEmployeeMaster(empInfo);
				dashboardresVO.setEmployeeDashboardVOS(employeeDashboardVOList);
				return appResponseService.genSuccessResponse(VaccinationConstants.VALID_USER, dashboardresVO);
			}
		}
		return appResponseService.genFailureResponse(VaccinationConstants.INVALID_USER, null);

	}
}
