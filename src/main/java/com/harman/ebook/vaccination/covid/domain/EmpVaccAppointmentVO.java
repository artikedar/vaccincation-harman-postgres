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
public class EmpVaccAppointmentVO {

    @JsonProperty("empVaccId")
    private Integer empVaccSchId;

    @JsonProperty("personId")
    private Integer personId;

    @JsonProperty("dateOfVaccination")
    private Date dateOfVaccination;

    @JsonProperty("status")
    private String status;

    @JsonProperty("isBookingActive")
    private Boolean isBookingActive;

    @JsonProperty("doseLevel")
    private Short doseLevel;

    @JsonProperty("location")
    private Short location;

    @JsonProperty("vacType")
    private Short vacType;

    @JsonProperty("slotNo")
    private Short slotNo;

}
