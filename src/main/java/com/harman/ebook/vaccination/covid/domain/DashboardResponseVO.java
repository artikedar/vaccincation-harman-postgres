package com.harman.ebook.vaccination.covid.domain;

import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Bhagwat Rokade
 * @project Ingestion
 * @created 6/2/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class DashboardResponseVO {
  EmployeeMaster employeeMaster;
  List<EmployeeDashboardVO> employeeDashboardVOS;

}
