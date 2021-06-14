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
public class PersonAppointmentVO {

    @JsonProperty("personId")
    private Integer personId;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("slotNo")
    private Short slotNo;

    @JsonProperty("empMasterId")
    private Integer empMasterId;

    @JsonProperty("slotName")
    private String slotName;

    @JsonProperty("empVaccAppId")
    private Integer empVaccAppId;

    @JsonProperty("manipalId")
    private String manipalId;

    @JsonProperty("cowinId")
    private String cowinId;
}
