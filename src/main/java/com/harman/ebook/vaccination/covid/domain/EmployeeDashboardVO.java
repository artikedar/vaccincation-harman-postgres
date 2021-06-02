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

    @JsonProperty("personAge")
    private Short personAge;

    @JsonProperty("isDoseI")
    private Boolean  isDoseI;

    @JsonProperty("dateOfDoseI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfDoseI;

    @JsonProperty("isDoseII")
    private Boolean isDoseII;

    @JsonProperty("dateOfDoseII")
    private Date dateOfDoseII;

    @JsonProperty("vacType")
    private Short vacType;

    @JsonProperty("location")
    private Short location;

    @JsonProperty("empMasterId")
    private Integer empMasterId;
 }

