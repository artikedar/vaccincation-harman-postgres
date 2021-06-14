package com.harman.ebook.vaccination.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeScheduledAppointmentVO {

    @JsonProperty("empMasterId")
    private Integer empMasterId;

    @JsonProperty("appointments")
    private List<AppointmentVO> appointments;
}
