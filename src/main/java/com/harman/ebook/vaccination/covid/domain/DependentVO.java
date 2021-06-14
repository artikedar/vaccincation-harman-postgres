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
public class DependentVO {

    @JsonProperty("personId")
    private Integer personId;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("manipalId")
    private String manipalId;

    @JsonProperty("cowinId")
    private String cowinId;

    @JsonProperty("dateOfVaccination")
    private String dateOfVaccination;

    @JsonProperty("vacType")
    private Short vacType;
}
