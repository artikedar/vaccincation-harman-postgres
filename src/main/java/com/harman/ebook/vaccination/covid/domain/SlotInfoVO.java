package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SlotInfoVO {
    @JsonProperty("slotInfoId")
    private Integer slotInfoId;

    @JsonProperty("location")
    private Short location;

    @JsonProperty("slotNo")
    private Short slotNo;

    @JsonProperty("totalNoOfDoses")
    private Short totalNoOfDoses;

    @JsonProperty("noOfBookedDoses")
    private Short noOfBookedDoses;

    @JsonProperty("noOfAvailableDoses")
    private Short noOfAvailableDoses;
}
