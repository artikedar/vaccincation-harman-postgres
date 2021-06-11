package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Bhagwat Rokade
 * @project Ingestion
 * @created 6/10/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeVO {

  @JsonProperty("empMasterId")
  private Integer empMasterId;

  @JsonProperty("employeeId")
  private String employeeId;

  @JsonProperty("fullname")
  private String fullname;

  @JsonProperty("employmenttype")
  private Short employmenttype;


}
