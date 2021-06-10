package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDashboardVO {
	@JsonProperty("personId")
    private Integer personId;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("cowinid")
    private String cowinid;

    @JsonProperty("manipalid")
    private String manipalid;

    @JsonProperty("isRegistered")
    private Boolean isRegistered;

    @JsonProperty("dateOfDoseI")
    private Date dateOfDoseI;

    @JsonProperty("empMasterId")
    private Integer empMasterId;

    @JsonProperty("empVacAppointmentInfo")
    private EmpVaccAppointmentVO empVaccAppointmentVO;
 }

