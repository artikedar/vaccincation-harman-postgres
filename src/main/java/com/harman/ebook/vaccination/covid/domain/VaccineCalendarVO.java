package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.harman.ebook.vaccination.covid.entity.SlotInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @JsonProperty("noOfBookedDoses")
    private Short noOfBookedDoses;

    @JsonProperty("noOfAvailableDoses")
    private Short noOfAvailableDoses;


    @JsonProperty("location")
    private Short location;

    @JsonProperty("slotInfoList")
    private List<SlotInfoVO> slotInfoList;

}
