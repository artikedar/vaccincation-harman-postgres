package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeReportVO {

    @JsonProperty("location")
    private Short location;

    @JsonProperty("dateOfVaccination")
    private String dateOfVaccination;

    @JsonProperty("personList")
    private List<PersonAppointmentVO> personList;
}
