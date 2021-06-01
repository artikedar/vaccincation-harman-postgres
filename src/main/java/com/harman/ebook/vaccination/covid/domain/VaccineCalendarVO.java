package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VaccineCalendarVO {
	@JsonProperty("vacType")
    private Short vacType;

    @JsonProperty("dateOfAvailability")
    private Date dateOfAvailability;

    @JsonProperty("noOfDoses")
    private Short noOfDoses;

    @JsonProperty("location")
    private Short location;
    
    

	public VaccineCalendarVO() {
		super();
	}



	public VaccineCalendarVO(Short vacType, Date dateOfAvailability, Short noOfDoses, Short location) {
		super();
		this.vacType = vacType;
		this.dateOfAvailability = dateOfAvailability;
		this.noOfDoses = noOfDoses;
		this.location = location;
	}



	public Short getVacType() {
		return vacType;
	}



	public void setVacType(Short vacType) {
		this.vacType = vacType;
	}



	public Date getDateOfAvailability() {
		return dateOfAvailability;
	}



	public void setDateOfAvailability(Date dateOfAvailability) {
		this.dateOfAvailability = dateOfAvailability;
	}



	public Short getNoOfDoses() {
		return noOfDoses;
	}



	public void setNoOfDoses(Short noOfDoses) {
		this.noOfDoses = noOfDoses;
	}



	public Short getLocation() {
		return location;
	}



	public void setLocation(Short location) {
		this.location = location;
	}
	
	
    
    
    
    
}
