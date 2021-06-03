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

    @JsonProperty("slotNo")
    private Short slotNo;

    @JsonProperty("empVaccAppId")
    private Integer empVaccAppId;

    @JsonProperty("dateOfVaccination")
    private Date dateOfVaccination;

}
