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
    
    
    

	public EmployeeDashboardVO() {
		super();
	}




	public EmployeeDashboardVO(Integer personId, String fullName, Short personAge, Boolean isDoseI, Date dateOfDoseI,
			Boolean isDoseII, Date dateOfDoseII, Short vacType, Short location, Integer empMasterId) {
		super();
		this.personId = personId;
		this.fullName = fullName;
		this.personAge = personAge;
		this.isDoseI = isDoseI;
		this.dateOfDoseI = dateOfDoseI;
		this.isDoseII = isDoseII;
		this.dateOfDoseII = dateOfDoseII;
		this.vacType = vacType;
		this.location = location;
		this.empMasterId = empMasterId;
	}




	public Integer getPersonId() {
		return personId;
	}




	public void setPersonId(Integer personId) {
		this.personId = personId;
	}




	public String getFullName() {
		return fullName;
	}




	public void setFullName(String fullName) {
		this.fullName = fullName;
	}




	public Short getPersonAge() {
		return personAge;
	}




	public void setPersonAge(Short personAge) {
		this.personAge = personAge;
	}




	public Boolean getIsDoseI() {
		return isDoseI;
	}




	public void setIsDoseI(Boolean isDoseI) {
		this.isDoseI = isDoseI;
	}




	public Date getDateOfDoseI() {
		return dateOfDoseI;
	}




	public void setDateOfDoseI(Date dateOfDoseI) {
		this.dateOfDoseI = dateOfDoseI;
	}




	public Boolean getIsDoseII() {
		return isDoseII;
	}




	public void setIsDoseII(Boolean isDoseII) {
		this.isDoseII = isDoseII;
	}




	public Date getDateOfDoseII() {
		return dateOfDoseII;
	}




	public void setDateOfDoseII(Date dateOfDoseII) {
		this.dateOfDoseII = dateOfDoseII;
	}




	public Short getVacType() {
		return vacType;
	}




	public void setVacType(Short vacType) {
		this.vacType = vacType;
	}




	public Short getLocation() {
		return location;
	}




	public void setLocation(Short location) {
		this.location = location;
	}




	public Integer getEmpMasterId() {
		return empMasterId;
	}




	public void setEmpMasterId(Integer empMasterId) {
		this.empMasterId = empMasterId;
	}
	
	
    
    

}
