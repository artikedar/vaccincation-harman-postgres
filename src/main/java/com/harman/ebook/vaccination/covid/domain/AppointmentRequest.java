package com.harman.ebook.vaccination.covid.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentRequest {

	@JsonProperty("empMasterId")
	private Integer empMasterId;

	@JsonProperty("location")
	private Short location;
	
	@JsonProperty("bookingDate")
	private String bookingDate;
	
	@JsonProperty("slotNo")
	private Short slotNo;
	
	@JsonProperty("personIds")
	private List<Integer> personIds;

	@JsonProperty("vacType")
	private Short vacType;
}
