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

}
