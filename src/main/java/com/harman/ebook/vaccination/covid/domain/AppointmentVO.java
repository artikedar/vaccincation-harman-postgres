package com.harman.ebook.vaccination.covid.domain;

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
public class AppointmentVO {

    @JsonProperty("personId")
    private Integer personId;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("manipalId")
    private String manipalid;

    @JsonProperty("cowinId")
    private String cowinid;

    @JsonProperty("dateOfVaccination")
    private String dateOfVaccination;

    @JsonProperty("vacType")
    private Short vacType;

    @JsonProperty("empVaccAppId")
    private Integer empVaccAppId;

    @JsonProperty("slotNo")
    private Short slotNo;

    @JsonProperty("slotName")
    private String slotName;

    @JsonProperty("location")
    private Short location;

    @JsonProperty("locationName")
    private String locationName;

    @JsonProperty("doseLevel")
    private Short doseLevel;

    @JsonProperty("isBookingActive")
    private Boolean isBookingActive;

    @JsonProperty("status")
    private Short status;
}
