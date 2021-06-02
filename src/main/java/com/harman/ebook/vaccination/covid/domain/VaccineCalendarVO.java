package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonDeserialize(as = VaccineCalendarVO.class)
public class VaccineCalendarVO implements Serializable {
	@JsonProperty("vacType")
    private Short vacType;

    @JsonProperty("dateOfAvailability")
    private String dateOfAvailability;

    @JsonProperty("noOfDoses")
    private Short noOfDoses;

    @JsonProperty("location")
    private Short location;

}
